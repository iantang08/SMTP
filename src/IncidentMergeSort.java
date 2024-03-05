import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncidentMergeSort {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

    public void sortIncidents(Incident[] incidents) throws ParseException {
        if (incidents.length < 2) {
            return;
        }
        int midIndex = incidents.length / 2;
        Incident[] leftHalf = new Incident[midIndex];
        Incident[] rightHalf = new Incident[incidents.length - midIndex];

        System.arraycopy(incidents, 0, leftHalf, 0, midIndex);
        System.arraycopy(incidents, midIndex, rightHalf, 0, incidents.length - midIndex);

        sortIncidents(leftHalf);
        sortIncidents(rightHalf);

        merge(incidents, leftHalf, rightHalf);
    }

    private void merge(Incident[] incidents, Incident[] leftHalf, Incident[] rightHalf) throws ParseException {
        int i = 0, j = 0, k = 0;

        while (i < leftHalf.length && j < rightHalf.length) {
            Date leftDate = dateFormat.parse(leftHalf[i].getDate());
            Date rightDate = dateFormat.parse(rightHalf[j].getDate());

            if (leftDate.after(rightDate)) {
                incidents[k++] = leftHalf[i++];
            } else {
                incidents[k++] = rightHalf[j++];
            }
        }

        while (i < leftHalf.length) {
            incidents[k++] = leftHalf[i++];
        }

        while (j < rightHalf.length) {
            incidents[k++] = rightHalf[j++];
        }
    }
}
