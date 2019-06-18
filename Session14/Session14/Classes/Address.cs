using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session14.Classes
{
    public class Address
    {
        public String Number { get; set; }
        public String Street { get; set; }
        public String Suburb { get; set; }
        public String Postcode { get; set; }
        public String State { get; set; }

        //Overload The == operator
        public static bool operator ==(Address x, Address y)
        {
            return object.Equals(x, y);
        }
        public static bool operator !=(Address x, Address y)
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
            Address rhs = obj as Address;
            return this.Number == rhs.Number && this.Street == rhs.Street && this.Suburb == rhs.Suburb && this.Postcode == rhs.Postcode && this.State == rhs.State;
        }
        public override int GetHashCode()
        {
            return this.Number.GetHashCode() ^ this.Street.GetHashCode() ^ this.Suburb.GetHashCode() ^ this.Postcode.GetHashCode() ^ this.State.GetHashCode();
        }

        //Constructor
        public Address(string number, string street, string suburb, string postcode, string state)
        {
            this.Number = number;
            this.Street = street;
            this.Suburb = suburb;
            this.Postcode = postcode;
            this.State = state;
        }
        public override string ToString()
        {
            return string.Format("{0} {1} {2} {3} {4}", Number, Street, Suburb, Postcode, State);
        }
    }
}
