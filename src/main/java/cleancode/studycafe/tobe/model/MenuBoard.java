package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

import java.util.List;

public class MenuBoard {

    private final List<StudyCafePass> studyCafePasses;
    private final List<StudyCafeLockerPass> lockerPasses;

    private MenuBoard(List<StudyCafePass> studyCafePasses, List<StudyCafeLockerPass> lockerPasses) {
        this.studyCafePasses = studyCafePasses;
        this.lockerPasses = lockerPasses;
    }

    public static MenuBoard from(StudyCafeFileHandler studyCafeFileHandler) {
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        List<StudyCafeLockerPass> studyCafeLockerPasses = studyCafeFileHandler.readLockerPasses();
        return new MenuBoard(studyCafePasses, studyCafeLockerPasses);
    }

    public List<StudyCafePass> getStudyCafePasses(StudyCafePassType hourly) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == hourly)
                .toList();
    }

    public StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass) {
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                        && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }
}
