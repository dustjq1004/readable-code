package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private List<StudyCafePass> studyCafePasses;
    private List<StudyCafeLockerPass> lockerPasses;


    public void initialize() {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        lockerPasses = studyCafeFileHandler.readLockerPasses();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                List<StudyCafePass> hourlyPasses = getStudyCafePasses(StudyCafePassType.HOURLY);
                outputHandler.showPassListForSelection(hourlyPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);

            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                List<StudyCafePass> weeklyPasses = getStudyCafePasses(StudyCafePassType.WEEKLY);
                outputHandler.showPassListForSelection(weeklyPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);

            } else if (studyCafePassType == StudyCafePassType.FIXED) {
                List<StudyCafePass> fixedPasses = getStudyCafePasses(StudyCafePassType.FIXED);
                outputHandler.showPassListForSelection(fixedPasses);

                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);
                StudyCafeLockerPass lockerPass = getLockerPass(selectedPass);

                boolean lockerSelection = isLockerSelection(lockerPass);
                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private boolean isLockerSelection(StudyCafeLockerPass lockerPass) {
        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }
        return lockerSelection;
    }

    private List<StudyCafePass> getStudyCafePasses(StudyCafePassType hourly) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == hourly)
                .toList();
    }

    private StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass) {
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }

}
