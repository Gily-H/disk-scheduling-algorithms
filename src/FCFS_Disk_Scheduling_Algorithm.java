/**
 * @file FCFS_Disk_Scheduling_Algorithm.java
 * @description this class will define a method that utilizes the First-Come-First-Serve
 * disk-scheduling algorithm to test the total number of head movements performed based on a given
 * set of disk I/O requests
 */

public class FCFS_Disk_Scheduling_Algorithm {

  /**
   * @param requestList      - array of the I/O requests
   * @param startingPosition - int denoting the starting cylinder
   * @return {int} total head movements performed
   * @description method that counts the total number of disk head movements performed on a given
   * list using the First-Come-First-Serve algorithm
   */
  public int FCFS_Algorithm(int[] requestList, int startingPosition) {
    int totalHeadMovements = 0; // counter for the total number of head movements
    int currentPosition = startingPosition; // update cylinder position as we move the head

    // iterate through the request list and move the head as each request come in
    for (int requestIdx = 0; requestIdx < requestList.length; requestIdx++) {
      System.out.printf("Current Position: %d\n", currentPosition);

      int request = requestList[requestIdx];
      System.out.printf("Incoming Request: %d\n", request);

      // find absolute difference between new request cylinder and current cylinder position
      // the difference will be the distance that the head has moved
      int positionDiff = Math.abs(currentPosition - request);
      System.out.printf("Disk Head Move Distance: %d\n", positionDiff);

      // add the difference to the total head movement count
      totalHeadMovements += positionDiff;

      // update the current position to equal the current request cylinder
      currentPosition = request;
      System.out.println();
    }

    return totalHeadMovements;
  }
}

class FCFS_TEST {
  public static void main(String[] args) {
    FCFS_Disk_Scheduling_Algorithm test = new FCFS_Disk_Scheduling_Algorithm();
    int fcfsTotalHeadMovements = test.FCFS_Algorithm(Data.DISK_IO_REQUESTS,
        Data.START_POSITION); // use the predetermined list of I/O requests
    System.out.printf("FCFS Total Head Movements: %d\n", fcfsTotalHeadMovements);
  }
}
