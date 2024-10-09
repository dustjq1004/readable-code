package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.MenuBoard;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler consoleInputHandler;
    private final OutputHandler consoleOutputHandler;
    private MenuBoard menuBoard;

    public StudyCafePassMachine(InputHandler consoleInputHandler, OutputHandler consoleOutputHandler) {
        this.consoleInputHandler = consoleInputHandler;
        this.consoleOutputHandler = consoleOutputHandler;
    }

    public void initialize() {
        menuBoard = MenuBoard.from(new StudyCafeFileHandler());
    }

    public void run() {
        try {
            consoleOutputHandler.showWelcomeMessage();
            consoleOutputHandler.showAnnouncement();

            consoleOutputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = consoleInputHandler.getPassTypeSelectingUserAction();

            List<StudyCafePass> studyCafePasses = menuBoard.getStudyCafePasses(studyCafePassType);
            consoleOutputHandler.showPassListForSelection(studyCafePasses);

            StudyCafePass selectedPass = consoleInputHandler.getSelectPass(studyCafePasses);
            StudyCafeLockerPass selectedLockerPass = getSelectedLockerPassBy(studyCafePassType, selectedPass);

            consoleOutputHandler.showPassOrderSummary(selectedPass, selectedLockerPass);
        } catch (AppException e) {
            consoleOutputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            consoleOutputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeLockerPass getSelectedLockerPassBy(StudyCafePassType studyCafePassType, StudyCafePass selectedPass) {
        if (studyCafePassType.isNotLockerPassType()) {
            return null;
        }

        StudyCafeLockerPass selectedLockerPass = menuBoard.getLockerPass(selectedPass);
        consoleOutputHandler.askLockerPass(selectedLockerPass);
        boolean lockerSelection = consoleInputHandler.getLockerSelection();

        if (lockerSelection) {
            return selectedLockerPass;
        }

        return null;
    }
}
