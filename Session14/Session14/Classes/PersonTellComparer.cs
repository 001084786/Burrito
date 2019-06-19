using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
    {
        class PersonTellComparer : Comparer<Person>
        {
            private static readonly PersonTellComparer _instance = new PersonTellComparer();
            public static PersonTellComparer Instance { get { return _instance; } }
            private PersonTellComparer() { }

            public override int Compare(Person x, Person y)
            {
                if (x == null && y == null)
                    return 0;
                if (x == null)
                    return -1;
                if (y == null)
                    return 1;
                return string.Compare(x.TelNum.ToUpperInvariant(), y.TelNum.ToUpperInvariant(), StringComparison.CurrentCulture);
            }
        }
    }
