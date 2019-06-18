using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    class PersonEqualityComparer : EqualityComparer<Person>
    {
        private static readonly PersonEqualityComparer _instance =
            new PersonEqualityComparer();
        public static PersonEqualityComparer Instance { get { return _instance; } }
        private PersonEqualityComparer() { }

        public override bool Equals(Person x, Person y)
        {
            return x.ID == y.ID;
        }

        public override int GetHashCode(Person obj)
        {
            return obj.ID.GetHashCode();
        }
    }
}
