using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    class CourseEqualityComparer : EqualityComparer<Course>
    {
        private static readonly CourseEqualityComparer _instance =
            new CourseEqualityComparer();
        public static CourseEqualityComparer Instance { get { return _instance; } }
        private CourseEqualityComparer() { }

        public override bool Equals(Course x, Course y)
        {
            return x.CourseCode.ToUpperInvariant() == y.CourseCode.ToUpperInvariant()
                && x.CourseName == y.CourseName;
        }

        public override int GetHashCode(Course obj)
        {
            return obj.CourseCode.ToUpperInvariant().GetHashCode() ^ obj.CourseName.GetHashCode();
        }
    }
}
