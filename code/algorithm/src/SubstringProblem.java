public class SubstringProblem {
    public static void main (String[] args) {
        String str = "this is a test string";
        String pat = "tist";
        System.out.print("Smallest window is: " + findSubString(str, pat));
        System.out.println();
    }

    private static String findSubString(String str, String pat) {
        int hashStr[] = new int[256];
        int hashPat[] = new int[256];
        int patLength = pat.length();

        for (int i=0;i<pat.length();i++) {
            hashPat[pat.charAt(i)]++;
        }

        int start=0;
        int count=0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int j=0;j<str.length();j++) {
            hashStr[str.charAt(j)]++;

            if(hashPat[str.charAt(j)]!=0 && hashStr[str.charAt(j)]<=hashPat[str.charAt(j)])
                count++;

            if (count==patLength) {
                while (hashStr[str.charAt(start)]>hashPat[str.charAt(start)] || hashPat[str.charAt(start)]==0) {
                    if (hashStr[str.charAt(start)]>hashPat[str.charAt(start)])
                        hashStr[str.charAt(start)]--;
                    start++;
                }
                int window = j - start + 1;
                if (window<minLength) {
                    minLength = window;
                    startIndex = start;
                }
            }

        }
        return str.substring(startIndex,startIndex+minLength);
    }
}
