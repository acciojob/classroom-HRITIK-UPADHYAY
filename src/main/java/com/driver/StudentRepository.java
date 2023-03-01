package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap;
    HashMap<String, Teacher> teacherHashMap;
    HashMap<String, List<String>> teacherStudentHashMap;

    public StudentRepository(){
        studentHashMap = new HashMap<>();
        teacherHashMap = new HashMap<>();
        teacherStudentHashMap = new HashMap<>();
    }


    public void addStudent(Student student){
        studentHashMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName){
        if(studentHashMap.containsKey(studentName) && teacherHashMap.containsKey(teacherName)){
            List<String> students = new ArrayList<>();
            if(teacherStudentHashMap.containsKey(teacherName)) students = teacherStudentHashMap.get(teacherName);
            students.add(studentName);
            teacherStudentHashMap.put(teacherName, students);
        }
    }

    public Student getStudentByName(String name){
        return studentHashMap.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherHashMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        return teacherStudentHashMap.get(teacherName);
    }

    public List<String> getAllStudents(){
        List<String> students = new ArrayList<>();
        for(String s : studentHashMap.keySet())  students.add(s);
        return students;
    }

    public void deleteTeacherByName(String teacherName){
        List<String> students = new ArrayList<>();

        if(teacherStudentHashMap.containsKey(teacherName)) {
            students = teacherStudentHashMap.get(teacherName);
            for(String s : students){    //student delete from student hashMap.
                if(studentHashMap.containsKey(s)) studentHashMap.remove(s);
            }

            teacherStudentHashMap.remove(teacherName); //teacher-student pair delete from teacherStudent HashMap.
        }

        if(teacherHashMap.containsKey(teacherName)) teacherHashMap.remove(teacherName); //teacher delete from teacher hashMap.

    }

    public void deleteAllTeachers(){
       HashSet<String> students = new HashSet<>();

       for(String teacherName : teacherStudentHashMap.keySet()){
           for(String studentName : teacherStudentHashMap.get(teacherName)){
               students.add(studentName);
           }
       }

       for(String s : students){
           if(studentHashMap.containsKey(s))
               studentHashMap.remove(s);
       }
    }
}
