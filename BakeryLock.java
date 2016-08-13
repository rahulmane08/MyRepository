package algorithms.locks;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BakeryLock implements Lock {

	/* Due to Java Memory Model, int[] not used for level and victim variables.
    Java programming language does not guarantee linearizability, or even sequential consistency,
    when reading or writing fields of shared objects
    [The Art of Multiprocessor Programming. Maurice Herlihy, Nir Shavit, 2008, pp.61.]
    */
   private AtomicBoolean[] flag;
   private AtomicInteger[] label;

   private int n;

   /**
    * Constructor for Bakery lock
    *
    * @param n thread count
    */
   public BakeryLock(int n) {
       this.n = n;
       flag = new AtomicBoolean[n];
       label = new AtomicInteger[n];
       for (int i = 0; i < n; i++) {
           flag[i] = new AtomicBoolean();
           label[i] = new AtomicInteger();
       }
   }

   /**
    * Acquires the lock.
    */
   @Override
   public void lock() {
       int i = Integer.parseInt(Thread.currentThread().getName());
       flag[i].set(true);
       label[i].set(findMaximumElement(label) + 1);
       for (int k = 0; k < n; k++) {
           while ((k != i) && flag[k].get() && ((label[k].get() < label[i].get()) || ((label[k].get() == label[i].get()) && k < i))) {
               //spin wait
           }
       }
   }

   /**
    * Releases the lock.
    */
   @Override
   public void unlock() {
       flag[Integer.parseInt(Thread.currentThread().getName())].set(false);
   }

   /**
    * Finds maximum element within and {@link java.util.concurrent.atomic.AtomicInteger} array
    *
    * @param elementArray element array
    * @return maximum element
    */
   private int findMaximumElement(AtomicInteger[] elementArray) {
       int maxValue = Integer.MIN_VALUE;
       for (AtomicInteger element : elementArray) {
           if (element.get() > maxValue) {
               maxValue = element.get();
           }
       }
       return maxValue;
   }

}
