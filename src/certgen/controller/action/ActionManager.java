package certgen.controller.action;

/**
 * Manager for available actions.
 * 
 * @author Dražen Đanić
 */
public class ActionManager {
	
	private ExitApplicationAction exitApplicationAction;
	private NewKeyStoreAction newKeyStoreAction;
	private OpenKeyStoreAction openKeyStoreAction;
	private SaveKeyStoreAction saveKeyStoreAction;
	private SaveAsKeyStoreAction saveAsKeyStoreAction;
	private NewCertificateAction newCertificateAction;
	private ExportCertificateAction exportCertificateAction;
	private ImportCertificateAction importCertificateAction;
	private ShowCertificateAction showCertificateAction;

	public ActionManager() {
		initializeActions();
	}
	
	private void initializeActions() {
		exitApplicationAction = new ExitApplicationAction();
		newKeyStoreAction = new NewKeyStoreAction();
		openKeyStoreAction = new OpenKeyStoreAction();
		saveKeyStoreAction = new SaveKeyStoreAction();
		saveAsKeyStoreAction = new SaveAsKeyStoreAction();
		newCertificateAction = new NewCertificateAction();
		exportCertificateAction = new ExportCertificateAction();
		importCertificateAction = new ImportCertificateAction();
		showCertificateAction = new ShowCertificateAction();
	}
	
	public void setExitApplicationAction(ExitApplicationAction exitApplicationAction) {
		this.exitApplicationAction = exitApplicationAction;
	}
	
	public ExitApplicationAction getExitApplicationAction() {
		return exitApplicationAction;
	}
	
	public void setNewKeyStoreAction(NewKeyStoreAction newKeyStoreAction) {
		this.newKeyStoreAction = newKeyStoreAction;
	}
	
	public NewKeyStoreAction getNewKeyStoreAction() {
		return newKeyStoreAction;
	}
	
	public void setOpenKeyStoreAction(OpenKeyStoreAction openKeyStoreAction) {
		this.openKeyStoreAction = openKeyStoreAction;
	}
	
	public OpenKeyStoreAction getOpenKeyStoreAction() {
		return openKeyStoreAction;
	}
	
	public void setSaveKeyStoreAction(SaveKeyStoreAction saveKeyStoreAction) {
		this.saveKeyStoreAction = saveKeyStoreAction;
	}
	
	public SaveKeyStoreAction getSaveKeyStoreAction() {
		return saveKeyStoreAction;
	}
	
	public void setSaveAsKeyStoreAction(SaveAsKeyStoreAction saveAsKeyStoreAction) {
		this.saveAsKeyStoreAction = saveAsKeyStoreAction;
	}
	
	public SaveAsKeyStoreAction getSaveAsKeyStoreAction() {
		return saveAsKeyStoreAction;
	}
	
	public void setNewCertificateAction(NewCertificateAction newCertificateAction) {
		this.newCertificateAction = newCertificateAction;
	}
	
	public NewCertificateAction getNewCertificateAction() {
		return newCertificateAction;
	}
	
	public void setExportCertificateAction(ExportCertificateAction exportCertificateAction) {
		this.exportCertificateAction = exportCertificateAction;
	}
	
	public ExportCertificateAction getExportCertificateAction() {
		return exportCertificateAction;
	}
	
	public void setImportCertificateAction(ImportCertificateAction importCertificateAction) {
		this.importCertificateAction = importCertificateAction;
	}
	
	public ImportCertificateAction getImportCertificateAction() {
		return importCertificateAction;
	}
	
	public void setShowCertificateAction(ShowCertificateAction showCertificateAction) {
		this.showCertificateAction = showCertificateAction;
	}
	
	public ShowCertificateAction getShowCertificateAction() {
		return showCertificateAction;
	}
	
}
