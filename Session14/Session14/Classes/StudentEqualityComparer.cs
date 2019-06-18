using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    class StudentEqualityComparer : EqualityComparer<Student>
    {
        private static readonly StudentEqualityComparer _instance =
            new StudentEqualityComparer();
        public static StudentEqualityComparer Instance { get { return _instance; } }
        private StudentEqualityComparer() { }

        public override bool Equals(Student x, Student y)
        {
            return x.ID == y.ID;
        }

        public override int GetHashCode(Student obj)
        {
            return obj.ID.GetHashCode();
        }
    }
}