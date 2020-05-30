public class Palindrome {

    public Deque<Character> wordToDeque(String word) {

        // iterate word and insert ino deque
        Deque<Character> toReturn = new LinkedListDeque<>();

        char[] chars = word.toCharArray();
        for (Character c : chars) {

            toReturn.addLast(c);
        }

        return toReturn;
    }

    public boolean isPalindrome(String word) {

        // base case: final check is the half length of the word
        // check the i and length - i - 1 and and return boolean

        Deque<Character> toCheck = wordToDeque(word);

        return isPalindrome(toCheck);
    }

    private boolean isPalindrome(Deque deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        Character first = (Character) deque.removeFirst();
        Character last = (Character) deque.removeLast();
        return first.equals(last) && isPalindrome(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {

        Deque<Character> toCheck = wordToDeque(word);

        return isPalindrome(toCheck, cc);

    }

    private boolean isPalindrome(Deque deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }
        Character first = (Character) deque.removeFirst();
        Character last = (Character) deque.removeLast();


        return cc.equalChars(first, last) && isPalindrome(deque);
    }



}
