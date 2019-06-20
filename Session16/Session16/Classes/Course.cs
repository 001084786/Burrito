using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session16.Classes
{
    public class Course
    {
        public String CourseCode { get; set; }
        public String CourseName { get; set; }
        public Double Cost { get; set; }

        //Overload The == operator
        public static bool operator ==(Course x, Course y)
        {
            return object.Equals(x, y);
        }
        public static bool operator !=(Course x, Course y)
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
            Course rhs = obj as Course;
            return this.CourseCode == rhs.CourseCode;
        }
        public override int GetHashCode()
        {
            return this.CourseCode.GetHashCode();
        }

        //Constructor
        public Course(string courseCode, string courseName, double cost)
        {
            this.CourseCode = courseCode;
            this.CourseName = courseName;
            this.Cost = cost;
        }
        public override string ToString()
        {
            return string.Format("{0} {1} {2}", CourseCode, CourseName, Cost);
        }
    }
}
