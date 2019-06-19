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
            //================================================================== CREATE LIST
            Console.WriteLine("Create an un-ordered list of students");
            Console.WriteLine();
            Address a1 = new Address("1", "Street St", "Subby", "4035", "SA");
            Person[] personList =
            {
                new Person("Nugget", "Nugget@gmail.com", "0400000000", a1),
                new Person("Chicken", "Chicken@gmail.com", "0411111111", a1),
                new Person("Salmon", "Salmon@gmail.com", "0422222222", a1),
                new Person("Pork", "Pork@gmail.com", "0433333333", a1),
                new Person("Beef", "Beef@gmail.com", "0444444444", a1),
                new Person("Lamb", "Lamb@gmail.com", "0455555555", a1),
                new Person("Pasta", "Pasta@gmail.com", "0466666666", a1),
                new Person("Playdough", "Playdough@gmail.com", "0477777777", a1),
                new Person("Sausage", "Sausage@gmail.com", "0488888888", a1),
                new Person("Prawn", "Prawn@gmail.com", "0499999999", a1)
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

        public static void ShowList(Person[] list)
        {
            foreach (var item in list)
                Console.WriteLine(item);
        }

        public static void BubbleSort(Person[] list)
        {
            Person t;

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

        public static string LinearSearch(Person[] list, string name)
        {
            for (int i = 0; i < list.Length; i++)
            {
                if (list[i].Name.ToUpperInvariant() == name.ToUpperInvariant())
                {
                    return "'" + name + "' has been found at position " + i + "\n Person: " + list[i];
                }
            }
            return "'" + name + "' not found";
        }

        public static string BinarySearch(Person[] list, string name)
        {

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
                    return "'" + name + "' found at position " + mid + "\n Person: " + list[mid];
                }
                if (min > max)
                    break;
            } while (min <= max);
            return "'" + name + "' not found";
        }
    }
}
