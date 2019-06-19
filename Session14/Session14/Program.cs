using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Session14.Classes;

namespace Session14
{
    class Program
    {
        static void Main(string[] args)
        {
            /* ============================== UNCOMMENT THIS SECTION TO VIEW THE COMPARE TO
            DateTime d1 = DateTime.Now;

            Console.WriteLine("Test Address Class");
            Address a1 = new Address("1", "Yeet St", "Bubble Base", "5000", "SA");
            Address a2 = new Address("1", "Yeet St", "Bubble Base", "5000", "SA");
            Address a3 = new Address("2", "One St", "Cable Camp", "5047", "NSW");

            Console.WriteLine("  a1 = " + a1);
            Console.WriteLine("  a2 = " + a2);
            Console.WriteLine("  a3 = " + a3);
            Console.WriteLine();
            Console.WriteLine("    Is a1 == a2: " + a1.CompareTo(a2) + " - Expected: True");
            Console.WriteLine("    Is a1 == a3: " + a1.CompareTo(a3) + " - Expected: False");
            Console.ReadKey();


            Console.WriteLine();
            Console.WriteLine("Test Person Class");
            Person p1 = new Person(1, "Jack", "JackJack@gmail.com", "0411111111", a1);
            Person p2 = new Person(1, "Jack", "JackJack@gmail.com", "0411111111", a1);
            Person p3 = new Person(2, "Bob", "BobBob@gmail.com", "0422222222", a1);

            Console.WriteLine("  p1 = " + p1);
            Console.WriteLine("  p2 = " + p2);
            Console.WriteLine("  p3 = " + p3);
            Console.WriteLine();
            Console.WriteLine("    Is p1 == p2: " + p1.CompareTo(p2) + " - Expected: True");
            Console.WriteLine("    Is p1 == p3: " + p1.CompareTo(p3) + " - Expected: False");
            Console.ReadKey();


            Console.WriteLine();
            Console.WriteLine("Test Course Class");
            Course c1 = new Course("5DD", "Database Design", 385.00);
            Course c2 = new Course("5DD", "Database Design", 385.00);
            Course c3 = new Course("5TSD", "Team Software Dev", 445.00);

            Console.WriteLine("  c1 = " + c1);
            Console.WriteLine("  c2 = " + c2);
            Console.WriteLine("  c3 = " + c3);
            Console.WriteLine();
            Console.WriteLine("    Is c1 == c2: " + c1.CompareTo(c2) + " - Expected: True");
            Console.WriteLine("    Is c1 == c3: " + c1.CompareTo(c3) + " - Expected: False");
            Console.ReadKey();


            Console.WriteLine();
            Console.WriteLine("Test Enrollment Class");
            Enrollment e1 = new Enrollment(1, d1, "A", "2", c1);
            Enrollment e2 = new Enrollment(1, d1, "A", "2", c2);
            Enrollment e3 = new Enrollment(2, d1, "B", "2", c3);

            Console.WriteLine("  e1 = " + e1);
            Console.WriteLine("  e2 = " + e2);
            Console.WriteLine("  e3 = " + e3);
            Console.WriteLine();
            Console.WriteLine("    Is e1 == e2: " + e1.CompareTo(e2) + " - Expected: True");
            Console.WriteLine("    Is e1 == e3: " + e1.CompareTo(e3) + " - Expected: False");
            Console.ReadKey();


            Console.WriteLine();
            Console.WriteLine("Test Student Class");
            Student s1 = new Student("5DD", d1, p1, e1);
            Student s2 = new Student("5DD", d1, p2, e2);
            Student s3 = new Student("5DD", d1, p3, e3);

            Console.WriteLine("  s1 = " + s1);
            Console.WriteLine("  s2 = " + s2);
            Console.WriteLine("  s3 = " + s3);
            Console.WriteLine();
            Console.WriteLine("    Is s1 == s2: " + s1.CompareTo(s2) + " - Expected: True");
            Console.WriteLine("    Is s1 == s3: " + s1.CompareTo(s3) + " - Expected: False");
            Console.ReadKey();
            */

            Address address1 = new Address("15", "NotSoFast St", "SonicHedgeHog", "5480", "SA");
            Person[] personList =
            {
                new Person(3, "Cold", "Ice@gmail.com", "0488888888", address1),
                new Person(1, "Ape", "SmallGorilla@gmail.com", "0422222222", address1),
                new Person(4, "Dank", "Memes@gmail.com", "0477777777", address1),
                new Person(2, "Bepis", "Pepsi@gmail.com", "0499999999", address1),
                new Person(1, "Ape", "SmallGorilla@gmail.com", "0422222222", address1)
            };

            ShowList(personList);
            Console.ReadKey();

            ShowAndSortNames(personList);
            Console.ReadKey();

            ShowAndSortEmails(personList);
            Console.ReadKey();

            ShowAndSortTell(personList);
            Console.ReadKey();

            ShowAndSortHash(personList);
            Console.ReadKey();
        }

        static void ShowList(Person[] list)
        {
            Console.WriteLine();
            Console.WriteLine("Unsorted List");

            foreach (var item in list)
                Console.WriteLine(item);
        }

        static void ShowAndSortNames(Person[] list)
        {
            Console.WriteLine();
            Console.WriteLine("Sort List by Name");

            Array.Sort(list, PersonNameComparer.Instance);
            foreach (var item in list)
                Console.WriteLine(item);
        }

        static void ShowAndSortEmails(Person[] list)
        {
            Console.WriteLine();
            Console.WriteLine("Sort List by Email");

            Array.Sort(list, PersonEmailComparer.Instance);
            foreach (var item in list)
                Console.WriteLine(item);
        }

        static void ShowAndSortTell(Person[] list)
        {
            Console.WriteLine();
            Console.WriteLine("Sort List by Tellephone Number");

            Array.Sort(list, PersonTellComparer.Instance);
            foreach (var item in list)
                Console.WriteLine(item);
        }

        static void ShowAndSortHash(Person[] list)
        {
            Console.WriteLine();
            Console.WriteLine("Sort List by HashSet Number");

            Array.Sort(list, PersonHashSetComparer.Instance);
            foreach (var item in list)
                Console.WriteLine(item);
        }
    }
}
