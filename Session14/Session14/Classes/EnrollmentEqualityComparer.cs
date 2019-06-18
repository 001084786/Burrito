using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    class EnrollmentEqualityComparer : EqualityComparer<Enrollment>
    {
        private static readonly EnrollmentEqualityComparer _instance =
            new EnrollmentEqualityComparer();
        public static EnrollmentEqualityComparer Instance { get { return _instance; } }
        private EnrollmentEqualityComparer() { }

        public override bool Equals(Enrollment x, Enrollment y)
        {
            return x.ID == y.ID;
        }

        public override int GetHashCode(Enrollment obj)
        {
            return obj.ID.GetHashCode();
        }
    }
}
