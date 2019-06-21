using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Session16.Classes;

namespace Session16
{
    class Program
    {
        static void Main(string[] args)
        {
            Address a1 = new Address("1", "Street St", "Subby", "4035", "SA");
            DateTime d1 = DateTime.Now;
            Course c1 = new Course("5DD", "Database Design", 100.00);
            Enrollment e1 = new Enrollment(1, d1, "A", "2", c1);


            //================================================================== CREATE LIST
            Console.WriteLine("Create an DoublyLinkedList of students");
            Console.WriteLine();

            DoublyLinkedList<Student> sList = new DoublyLinkedList<Student>();
            sList.AddLast(new Student("Program", d1, "Nugget", "Nugget@gmail.com", "0400000000", a1, e1));
            sList.AddLast(new Student("Program", d1, "Chicken", "Chicken@gmail.com", "0411111111", a1, e1));
            sList.AddLast(new Student("Program", d1, "Salmon", "Salmon@gmail.com", "0422222222", a1, e1));
            sList.AddLast(new Student("Program", d1, "Pork", "Pork@gmail.com", "0433333333", a1, e1));
            sList.AddLast(new Student("Program", d1, "Beef", "Beef@gmail.com", "0444444444", a1, e1));
            sList.AddLast(new Student("Program", d1, "Lamb", "Lamb@gmail.com", "0455555555", a1, e1));
            sList.AddLast(new Student("Program", d1, "Pasta", "Pasta@gmail.com", "0466666666", a1, e1));
            sList.AddLast(new Student("Program", d1, "Playdough", "Playdough@gmail.com", "0477777777", a1, e1));
            sList.AddLast(new Student("Program", d1, "Sausage", "Sausage@gmail.com", "0488888888", a1, e1));
            sList.AddLast(new Student("Program", d1, "Prawn", "Prawn@gmail.com", "0499999999", a1, e1));

            //========================= Enumerate through list and display
            Console.WriteLine("Display DoublyLinkedList of students");
            Console.WriteLine();
            EnumerateAndDisplay(sList);
            Console.ReadKey();
            Console.WriteLine();
            Console.WriteLine();

            //========================= Add at end + Display
            Console.WriteLine("Add at end then display DoublyLinkedList of students");
            Console.WriteLine();
            sList.AddLast(new Student("YAY", d1, "NEW PERSON", "NEW@gmail.com", "0412345678", a1, e1));
            EnumerateAndDisplay(sList);
            Console.ReadKey();
            Console.WriteLine();
            Console.WriteLine();

            //========================= Find student in list
            Console.WriteLine("Find student in list");
            Console.Write("Insert Student Name: ");
            string name = Console.ReadLine();
            Console.WriteLine(LinearSearchDLL(sList, name));
            Console.ReadKey();
            Console.WriteLine();
            Console.WriteLine();

            //========================= Remove at start + Display
            Console.WriteLine("Remove at start then display DoublyLinkedList of students");
            Console.WriteLine();
            sList.RemoveFirst();
            EnumerateAndDisplay(sList);
            Console.ReadKey();
            Console.WriteLine();
            Console.WriteLine();

            //========================= Remove at start + Display
            Console.WriteLine("Remove at end then display DoublyLinkedList of students");
            Console.WriteLine();
            sList.RemoveLast();
            EnumerateAndDisplay(sList);
            Console.ReadKey();
            Console.WriteLine();
            Console.WriteLine();
        }

        public static void EnumerateAndDisplay(DoublyLinkedList<Student> list)
        {
            Classes.LinkedListNode<Student> node = list.Head;
            while (node != null)
            {
                Console.WriteLine(node.Value);
                node = node.Next;
            }
        }

        public static string LinearSearchDLL(DoublyLinkedList<Student> list, string name)
        {
            Classes.LinkedListNode<Student> node = list.Head;

            bool found = false;
            while (node != null)
            {
                if (node.Value.Name.ToUpperInvariant() == name.ToUpperInvariant())
                {
                    return "'" + name + "' has been found \n Student: " + node.Value;
                }
                node = node.Next;
            }
            return "'" + name + "' not found";
        }
    }
}
