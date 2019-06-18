using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    class AddressEqualityComparer : EqualityComparer<Address>
    {
        private static readonly AddressEqualityComparer _instance =
            new AddressEqualityComparer();
        public static AddressEqualityComparer Instance { get { return _instance; } }
        private AddressEqualityComparer() { }

        public override bool Equals(Address x, Address y)
        {
            return x.Number == y.Number
                && x.Postcode == y.Postcode
                && x.State == y.State
                && x.Street == y.Street
                && x.Suburb == y.Suburb;
        }

        public override int GetHashCode(Address obj)
        {
            return obj.Number.GetHashCode() ^ obj.Postcode.GetHashCode() ^ obj.State.GetHashCode() ^ obj.Street.GetHashCode() ^ obj.Suburb.GetHashCode();
        }
    }
}
