using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Session15.Classes;

namespace Session15
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
            Console.WriteLine("Create an un-ordered list of students");
            Console.WriteLine();
            Student[] personList =
            {
                new Student("Program", d1, "Nugget", "Nugget@gmail.com", "0400000000", a1, e1),
                new Student("Program", d1, "Chicken", "Chicken@gmail.com", "0411111111", a1, e1),
                new Student("Program", d1, "Salmon", "Salmon@gmail.com", "0422222222", a1, e1),
                new Student("Program", d1, "Pork", "Pork@gmail.com", "0433333333", a1, e1),
                new Student("Program", d1, "Beef", "Beef@gmail.com", "0444444444", a1, e1),
                new Student("Program", d1, "Lamb", "Lamb@gmail.com", "0455555555", a1, e1),
                new Student("Program", d1, "Pasta", "Pasta@gmail.com", "0466666666", a1, e1),
                new Student("Program", d1, "Playdough", "Playdough@gmail.com", "0477777777", a1, e1),
                new Student("Program", d1, "Sausage", "Sausage@gmail.com", "0488888888", a1, e1),
                new Student("Program", d1, "Prawn", "Prawn@gmail.com", "0499999999", a1, e1)
            };

            ShowList(personList);
            Console.WriteLine();
            Console.WriteLine("Press Any Key To Continue...");
            Console.ReadKey();
            Console.WriteLine();


            //================================================================== SORT LIST "BUBBLE SORT"
            Console.WriteLine("Sort List using Bubble Sort");
            Console.WriteLine();
            BubbleSort(personList);
            ShowList(personList);
            Console.WriteLine();
            Console.WriteLine("Press Any Key To Continue...");
            Console.ReadKey();
            Console.WriteLine();


            //================================================================== LINEAR SEARCH
            Console.WriteLine("Linear Search for Name");
            Console.Write("Please Insert Name: ");
            string name = Console.ReadLine();
            Console.WriteLine();
            Console.WriteLine(LinearSearch(personList, name));
            Console.WriteLine("Press Any Key To Continue...");
            Console.ReadKey();
            Console.WriteLine();


            //================================================================== LINEAR SEARCH
            Console.WriteLine("Binary Search for Name");
            Console.Write("Please Insert Name: ");
            name = Console.ReadLine();
            Console.WriteLine();
            Console.WriteLine(BinarySearch(personList, name));
            Console.WriteLine("Press Any Key To Continue...");
            Console.ReadKey();
            Console.WriteLine();



        }

        public static void ShowList(Student[] list)
        {
            foreach (var item in list)
                Console.WriteLine(item);
        }

        public static void BubbleSort(Student[] list)
        {
            Student t;

            for (int j = 0; j <= list.Length - 1; j++)
            {
                for (int i = 0; i < list.Length - 1; i++)
                {
                    if (PersonNameComparer.Instance.Compare(list[i], list[i + 1]) == 1) //IF list[i] > list[i+1]
                    {
                        t = list[i + 1];
                        list[i + 1] = list[i];
                        list[i] = t;
                    }
                }
            }//End For
        }//End Bubble

        public static string LinearSearch(Student[] list, string name)
        {
            for (int i = 0; i < list.Length; i++)
            {
                if (list[i].Name.ToUpperInvariant() == name.ToUpperInvariant())
                {
                    return "'" + name + "' has been found at position " + i + "\n Student: " + list[i];
                }
            }
            return "'" + name + "' not found";
        }

        public static string BinarySearch(Student[] list, string name)
        {
            //SORT FIRST
            BubbleSort(list);

            int min = 0;
            int max = list.Length - 1;
            do
            {
                int mid = (min + max) / 2;
                if (PersonNameComparer.Instance.CompareName(name, list[mid].Name) == 1)
                    min = mid + 1;
                else
                    max = mid - 1;
                if (list[mid].Name.ToUpperInvariant() == name.ToUpperInvariant())
                {
                    return "'" + name + "' found at position " + mid + "\n Student: " + list[mid];
                }
                if (min > max)
                    break;
            } while (min <= max);
            return "'" + name + "' not found";
        }
    }
}
