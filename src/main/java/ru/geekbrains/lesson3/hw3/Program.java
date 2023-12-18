package ru.geekbrains.lesson3.hw3;

import java.io.*;

public class Program {
    /*
    Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
    Обеспечьте поддержку сериализации для этого класса.
    Создайте объект класса Student и инициализируйте его данными.
    Сериализуйте этот объект в файл.
    Десериализуйте объект обратно в программу из файла.
    Выведите все поля объекта, включая GPA, и обсудите,
    почему значение GPA не было сохранено/восстановлено.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student_1 = new Student("Jack", 20, 4.7);

        //Сериализация
        try(FileOutputStream fileOutputStream = new FileOutputStream("student_1.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(student_1);
            System.out.println("Объект student_1 сериализован.");
        }

        //Десериализация
        try(FileInputStream fileInputStream = new FileInputStream("student_1.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            student_1 = (Student)objectInputStream.readObject();
            System.out.println("Объект student_1 десериализован.");
        }

        System.out.println("Name: " + student_1.getName());
        System.out.println("Age: " + student_1.getAge());
        System.out.println("GPA(значение 0.0, т.к в классе Student поле GPA имеет модификатор transient,\n" +
                "что означает поле не должно быть сериализованно. Подобные поля нужно вычислять программно.): " + student_1.getGPA());
    }
}
