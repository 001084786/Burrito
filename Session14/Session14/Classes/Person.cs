using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    public class Person : IComparable<Person>
    {
        public int ID { get; set; }
        public String Name { get; set; }
        public String Email { get; set; }
        public String TelNum { get; set; }
        public Address Address { get; set; }

        //Overload The == operator
        public static bool operator ==(Person x, Person y)
        {
            return object.Equals(x, y);
        }
        public static bool operator !=(Person x, Person y)
        {
            return !object.Equals(x, y);
        }

        //Override the Equals() operator
        public override bool Equals(object obj)
        {
            if (obj == null)
                return false;
            if (ReferenceEquals(obj, this))
                return true;
            if (obj.GetType() != this.GetType())
                return false;
            Person rhs = obj as Person;
            return this.Name == rhs.Name && this.Email == rhs.Email && this.TelNum == rhs.TelNum;
        }
        public override int GetHashCode()
        {
            return this.Name.GetHashCode() ^ this.Email.GetHashCode() ^ this.TelNum.GetHashCode();
        }

        //Constructor
        public Person(int id, string name, string email, string telNum, Address address)
        {
            this.ID = id;
            this.Name = name;
            this.Email = email;
            this.TelNum = telNum;
            this.Address = address;
        }
        public override string ToString()
        {
            return string.Format("{0} {1} {2} {3} {4}", ID, Name, Email, TelNum, Address);
        }

        public int CompareTo(Person obj)
        {
            if (obj == null)
                return 1;
            if (obj != null)
                return this.ID.CompareTo(obj.ID);
            else
                throw new ArgumentException("Object is not a person");
        }
    }
}
