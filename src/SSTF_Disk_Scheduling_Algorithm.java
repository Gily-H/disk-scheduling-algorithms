import java.util.Arrays;

/**
 * @file SSTF_Disk_Scheduling_Algorithm.java
 * @description this class will define a method that utilizes the Shortest-Seek-Time-First
 * disk-scheduling algorithm to test the total number of head movements performed based on a given
 * set of disk I/O requests
 */

public class SSTF_Disk_Scheduling_Algorithm {

  /**
   * @param requestList      - array of incoming I/O requests
   * @param startingPosition - int starting position of the disk head
   * @return {int} total head movements performed
   * @description method that counts the total number of Disk Head movements performed on a given
   * list using the Shortest-Seek-Time-First algorithm
   */
  public int SSTF_Algorithm(int[] requestList, int startingPosition) {
    // tracker array to check if the request at a corresponding index has already been processed
    // True value if request already processed, False value if not yet processed
    // this array will be used to avoid checking the same request more than once
    boolean[] alreadyAccessed = new boolean[requestList.length];
    Arrays.fill(alreadyAccessed, false); // fill the tracker array with initial False values

    int totalHeadMovements = 0;
    int currentPosition = startingPosition;
    int currentMinSeekIndex = 0;
    int currentMinSeekPosition = 0;

    // loop the length of the request list to process all requests
    for (int i = 0; i < requestList.length; i++) {
      int minSeekDistance = Data.MAX_CYLINDERS; // initialize with MAX seek distance possible

      // iterate through the request list
      for (int requestIdx = 0; requestIdx < requestList.length; requestIdx++) {

        // if the current request index has not yet been accessed/processed
        if (!alreadyAccessed[requestIdx]) {
          int request = requestList[requestIdx];

          // find the diff distance between incoming request and the current Head position
          int positionDiff = Math.abs(currentPosition - request);

          // if diff distance is less than the current minimum seek distance
          if (positionDiff <= minSeekDistance) {

            // update the min seek distance value
            minSeekDistance = positionDiff;

            // save the index of the current request with the shorter seek distance
            currentMinSeekIndex = requestIdx;

            // update the minimum seek position
            currentMinSeekPosition = request;

          }
        }
      }

      System.out.printf("Current Position: %d\n", currentPosition);
      System.out.printf("Incoming Request: %d\n", requestList[currentMinSeekIndex]);
      System.out.printf("Shortest Seek Time: %d\n", minSeekDistance);
      System.out.println();

      // update the current position of the Disk Head when we find the next Shortest Seek time
      currentPosition = currentMinSeekPosition;

      // add the current min seek distance to the total head movement count
      totalHeadMovements += minSeekDistance;

      // update tracker array at the request index of the current min seek distance, set value to true
      alreadyAccessed[currentMinSeekIndex] = true;
    }

    return totalHeadMovements;
  }
}

class SSTF_TEST {
  public static void main(String[] args) {
    SSTF_Disk_Scheduling_Algorithm test = new SSTF_Disk_Scheduling_Algorithm();
    int sstfTotalHeadMovements = test.SSTF_Algorithm(Data.DISK_IO_REQUESTS,
        Data.START_POSITION); // use the predetermined list of I/O requests
    System.out.printf("SSTF Total Head Movements: %d\n", sstfTotalHeadMovements);
  }
}
