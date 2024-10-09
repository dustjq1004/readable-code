package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.MenuBoard;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private MenuBoard menuBoard;


    public void initialize() {
        menuBoard = MenuBoard.from(new StudyCafeFileHandler());
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> studyCafePasses = menuBoard.getStudyCafePasses(studyCafePassType);
            outputHandler.showPassListForSelection(studyCafePasses);

            StudyCafePass selectedPass = inputHandler.getSelectPass(studyCafePasses);
            StudyCafeLockerPass selectedLockerPass = getSelectedLockerPassBy(studyCafePassType, selectedPass);

            outputHandler.showPassOrderSummary(selectedPass, selectedLockerPass);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeLockerPass getSelectedLockerPassBy(StudyCafePassType studyCafePassType, StudyCafePass selectedPass) {
        if (studyCafePassType != StudyCafePassType.FIXED) {
            return null;
        }

        StudyCafeLockerPass selectedLockerPass = menuBoard.getLockerPass(selectedPass);
        outputHandler.askLockerPass(selectedLockerPass);
        boolean lockerSelection = inputHandler.getLockerSelection();

        return selectedLockerPass;
    }
}
