public class Methods {

    public String random(String no) {
        char[] sayi = no.toCharArray();
        for (int a = sayi.length - 1; a >= 0; a--) {
            if (sayi[a] == 'z') {
                sayi[a] = 'a';
            } else if (sayi[a] == '9') {
                sayi[a] = '0';
            } else {
                sayi[a]++;
                break;
            }
        }
        return String.valueOf(sayi);
    }
}
