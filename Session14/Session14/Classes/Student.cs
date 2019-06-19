using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    public sealed class Student : Person
    {
        public String Program { get; set; }
        public DateTime DateRegistered { get; set; }
        public Enrollment Enrollment { get; set; }
        

        //Overload The == operator
        public static bool operator ==(Student x, Student y)
        {
            return object.Equals(x, y);
        }
        public static bool operator !=(Student x, Student y)
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
            Student rhs = obj as Student;
            return this.Name == rhs.Name && this.Email == rhs.Email && this.TelNum == rhs.TelNum;
        }
        public override int GetHashCode()
        {
            return base.GetHashCode() ^ this.Program.GetHashCode() ^ this.DateRegistered.GetHashCode();
        }

        //Constructor
        public Student(string program, DateTime dateRegistered, int id, string name, string email, string telNum, Address address, Enrollment enrollment)
            : base(id, name, email, telNum, address)
        {
            this.Program = program;
            this.DateRegistered = dateRegistered;
            this.Enrollment = enrollment;
        }
        public Student(string program, DateTime dateRegistered, Person p, Enrollment enrollment)
            : this(program, dateRegistered, p.ID, p.Name, p.Email, p.TelNum, p.Address, enrollment) { }

        // toString()
        public override string ToString()
        {
            return string.Format("{0} {1}", Program, DateRegistered.ToString("dd/MM/yyyy"));
        }
    }
}
