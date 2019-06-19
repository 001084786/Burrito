using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
    {
        class PersonHashSetComparer : Comparer<Person>
        {
            private static readonly PersonHashSetComparer _instance = new PersonHashSetComparer();
            public static PersonHashSetComparer Instance { get { return _instance; } }
            private PersonHashSetComparer() { }

            public override int Compare(Person x, Person y)
            {
                if (x == null && y == null)
                    return 0;
                if (x == null)
                    return -1;
                if (y == null)
                    return 1;
                return string.Compare(x.GetHashCode().ToString(), y.TelNum.GetHashCode().ToString(), StringComparison.CurrentCulture);
            }
        }
    }
