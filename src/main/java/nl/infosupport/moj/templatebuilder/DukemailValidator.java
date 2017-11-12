package nl.infosupport.moj.templatebuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukemailValidator {

    public boolean isValid(String dukeEmailAddress) {
        boolean isEmailStructureValid = this.validateDukeEmailStructure(dukeEmailAddress);
        if (!isEmailStructureValid)
            return false;

        String[] emailParts = dukeEmailAddress.split("@");
        String localPart = emailParts[0];
        String domainPart = emailParts[1];

        boolean isLocalPartValid = this.validateLocalPart(localPart);
        boolean isDomainPartValid = this.validateDomainPart(domainPart);

        return isLocalPartValid && isDomainPartValid;
    }

    private boolean validateDukeEmailStructure(String dukeEmailAddress) {
        if (dukeEmailAddress == null)
            return false;

        if (!dukeEmailAddress.contains("@"))
            return false;

        Pattern pattern = Pattern.compile("[@]");
        Matcher matcher = pattern.matcher(dukeEmailAddress);
        int atSignOccurrences = 0;
        while (matcher.find()) {
            if (atSignOccurrences > 1)
                return false;

            atSignOccurrences++;
        }

        return true;
    }

    private boolean validateDomainPart(String domainPart) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9.]*");
        Matcher matcher = pattern.matcher(domainPart);
        if (!matcher.matches())
            return false;

        int dotOccurrences = 0;
        for (char c : domainPart.toCharArray()) {
            if (dotOccurrences > 1)
                return false;

            if (c == '.')
                dotOccurrences++;
        }

        if (!domainPart.endsWith(".dukemail"))
            return false;

        return true;
    }

    private boolean validateLocalPart(String localPart) {
        if (localPart.charAt(0) == '.' || localPart.charAt(localPart.length() - 1) == '.')
            return false;

        Pattern pattern = Pattern.compile("[a-zA-Z0-9.]*");
        Matcher matcher = pattern.matcher(localPart);
        if (!matcher.matches())
            return false;

        char[] charArray = localPart.toCharArray();
        for (int index = 0; index < localPart.toCharArray().length; index++) {
            if (index == 0 || index == localPart.toCharArray().length - 1)
                continue;

            char currentChar = charArray[index];
            char previousChar = charArray[index - 1];
            char nextChar = charArray[index + 1];

            if (currentChar == previousChar || currentChar == nextChar)
                return false;
        }

        return true;
    }
}