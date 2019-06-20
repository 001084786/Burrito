using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Session16.Classes
{
    public class DoublyLinkedList<T> :
        ICollection<T>
    {
        public LinkedListNode<T> Head { get; set; }
        public LinkedListNode<T> Tail { get; set; }

        public void AddFirst(T value)
        {
            AddFirst(new LinkedListNode<T>(value));
        }

        public void AddFirst(LinkedListNode<T> node)
        {
            // Save off the head node so we dont lose it
            LinkedListNode<T> temp = Head;

            // Point head to new node
            Head = node;

            // Insert rest of list behind head
            Head.Next = temp;

            if (Count == 0)
            {
                // if the list was emmpty then Head and Tail should
                // both point to the new node.
                Tail = Head;
            }
            else
            {
                temp.Previous = Head;
            }

            Count++;
        }
        public void AddLast(T value)
        {
            AddLast(new LinkedListNode<T>(value));
        }
        public void AddLast(LinkedListNode<T> node)
        {
            if (Count == 0)
            {
                Head = node;
            }
            else
            {
                Tail.Next = node;
                node.Previous = Tail;
            }

            Tail = node;
            Count++;
        }
        public void RemoveFirst()
        {
            if (Count != 0)
            {
                Head = Head.Next;

                Count--;

                if (Count == 0)
                {
                    Tail = null;
                }
                else
                {
                    Head.Previous = null;
                }
            }
        }
        public void RemoveLast()
        {
            if (Count != 0)
            {
                Count--;

                if (Count == 1)
                {
                    Head = null;
                    Tail = null;
                }
                else
                {
                    Tail.Previous = null;
                    Tail = Tail.Previous;
                }
                Head = Head.Next;

                Count--;
            }
        }

        public int Count { get; set; }

        public void Add(T item)
        {
            AddFirst(item);
        }
        public bool Contains(T item)
        {
            LinkedListNode<T> current = Head;
            while (current != null)
            {
                if (current != null)
                {
                    return true;
                }
                current = current.Next;
            }
            return false;
        }
        public void CopyTo(T[] array, int arrayIndex)
        {
            LinkedListNode<T> current = Head;
            while (current != null)
            {
                array[arrayIndex++] = current.Value;
                current = current.Next;
            }
        }
    }
}

