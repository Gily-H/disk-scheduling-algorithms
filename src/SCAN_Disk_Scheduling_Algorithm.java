import java.util.ArrayList;
import java.util.Arrays;

/**
 * @file SCAN_Disk_Scheduling_Algorithm.java
 * @description this class will define a method that utilizes the SCAN disk-scheduling algorithm to
 * test the total number of head movements performed based on a given set of disk I/O requests
 */

public class SCAN_Disk_Scheduling_Algorithm {

  /**
   * @param requestList      - array of incoming I/O requests
   * @param startingPosition - int starting position of the disk head
   * @return {int} total head movements performed
   * @description method that counts the total number of Disk Head movements performed on a given
   * list using the SCAN algorithm
   */
  public int SCAN_Algorithm(int[] requestList, int startingPosition) {
    int totalHeadMovements = 0;
    int currentPosition = startingPosition;
    int indexOfStartingPosition = -1;

    // sort the request list - sorting will help us order the requests by minimum seek time
    Arrays.sort(requestList);

    // if the first request is greater than the starting position, we know the disk moves to the right only
    if (requestList[0] > startingPosition) {
      indexOfStartingPosition = 0;
    } else if (requestList[requestList.length - 1] < startingPosition) {
      // if the last request is less than the starting position, we know the disk moves to the left only
      indexOfStartingPosition = requestList.length - 1;
    } else {
      // find the index where the starting position cylinder would reside in the request list
      for (int i = 0; i < requestList.length - 1; i++) {
        int request = requestList[i];
        int nextRequest = requestList[i + 1];

        if (startingPosition > request && startingPosition < nextRequest) {
          indexOfStartingPosition = i;
        }
      }
    }

    // indexOfStartingPosition = left side
    if (indexOfStartingPosition == -1) {
      System.out.println("Error with disk scheduling algorithm");
      return indexOfStartingPosition;
    }

    // if the index of the starting position is greater than half the length of the request list
    // we have fewer requests going towards the right end
    if (indexOfStartingPosition > requestList.length / 2) {
      // start processing requests that go to the right end and add the movements to total
      totalHeadMovements += processRequests(requestList, indexOfStartingPosition, false, currentPosition);
      // then process requests going back to the left end and add movements to total
      totalHeadMovements += processRequests(requestList, indexOfStartingPosition, true, currentPosition);
    } else { // else if the index of the starting position is less than half of the request list length
      // we have fewer requests going towards the left end
      // we process requests going left first and add movements to total
      totalHeadMovements += processRequests(requestList, indexOfStartingPosition, true, currentPosition);
      // then process requests until we reach the right end and add movements to total
      totalHeadMovements += processRequests(requestList, indexOfStartingPosition, false, currentPosition);
    }

    return totalHeadMovements;
  }

  public int processRequests(int[] requestList, int startingIndex, boolean goingLeft,
      int currentPosition) {
    int totalMovements = 0;
    int position = currentPosition;

    if (goingLeft) {
      for (int i = startingIndex; i > 0; i--) {
        int request = requestList[i];
        int positionDiff = Math.abs(position - request);
        printRequest(request, position, positionDiff);
        totalMovements += positionDiff;
        position = request;
      }
    } else {
      for (int i = startingIndex; i < requestList.length; i++) {
        int request = requestList[i];
        int positionDiff = Math.abs(position - request);
        printRequest(request, position, positionDiff);
        totalMovements += positionDiff;
        position = request;
      }
    }
    return totalMovements;
  }

  public void printRequest(int request, int currentPosition, int positionDiff) {
    System.out.printf("Current Position: %d\n", currentPosition);
    System.out.printf("Incoming Request: %d\n", request);
    System.out.printf("Shortest Seek Time: %d\n", positionDiff);
    System.out.println();
  }
}

class SCAN_TEST {

  public static void main(String[] args) {
    SCAN_Disk_Scheduling_Algorithm test = new SCAN_Disk_Scheduling_Algorithm();
    int scanTotalHeadMovements = test.SCAN_Algorithm(Data.DISK_IO_REQUESTS, Data.START_POSITION);
    System.out.printf("SCAN Total Head Movements: %d\n", scanTotalHeadMovements);
  }
}
