using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session13.Classes
{
    public class Person
    {
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
        public Person(string name, string email, string telNum, Address address)
        {
            this.Name = name;
            this.Email = email;
            this.TelNum = telNum;
            this.Address = address;
        }
        public override string ToString()
        {
            return string.Format("{0} {1} {2} {3}", Name, Email, TelNum, Address);
        }
    }
}
