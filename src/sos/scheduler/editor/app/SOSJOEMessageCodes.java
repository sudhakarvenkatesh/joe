package sos.scheduler.editor.app;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/*import sos.scheduler.editor.conf.forms.already;
import sos.scheduler.editor.conf.forms.be;
import sos.scheduler.editor.conf.forms.must;
import sos.scheduler.editor.conf.forms.with;
*/
//import sos.scheduler.editor.conf.forms.Executed;
//import sos.scheduler.editor.conf.forms.on;

import com.sos.i18n.annotation.I18NMsg;

public class SOSJOEMessageCodes extends Composite {

	// BaseForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_BaseForm_BaseFiles							= new SOSMsgJOE("JOE_G_BaseForm_BaseFiles");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_BaseForm_BaseFile								= new SOSMsgJOE("JOE_L_BaseForm_BaseFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_BaseForm_BaseFile								= new SOSMsgJOE("JOE_T_BaseForm_BaseFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_Apply								= new SOSMsgJOE("JOE_B_BaseForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_BaseForm_BaseComment							= new SOSMsgJOE("JOE_L_BaseForm_BaseComment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_BaseForm_BaseComment							= new SOSMsgJOE("JOE_T_BaseForm_BaseComment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_BaseForm_CommentOpen						= new SOSMsgJOE("JOE_Cmp_BaseForm_CommentOpen");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_Comment								= new SOSMsgJOE("JOE_B_BaseForm_Comment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_OpenFileDialog						= new SOSMsgJOE("JOE_B_BaseForm_OpenFileDialog");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_BaseForm_S1									= new SOSMsgJOE("JOE_Sep_BaseForm_S1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_NewBaseFile							= new SOSMsgJOE("JOE_B_BaseForm_NewBaseFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_OpenBaseFile							= new SOSMsgJOE("JOE_B_BaseForm_OpenBaseFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_BaseForm_S2									= new SOSMsgJOE("JOE_Sep_BaseForm_S2");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_BaseForm_RemoveBaseFile						= new SOSMsgJOE("JOE_B_BaseForm_RemoveBaseFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_BaseForm_BaseTable							= new SOSMsgJOE("JOE_Tbl_BaseForm_BaseTable");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_BaseForm_BaseFiles							= new SOSMsgJOE("JOE_TCl_BaseForm_BaseFiles");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_BaseForm_BaseComment						= new SOSMsgJOE("JOE_TCl_BaseForm_BaseComment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_FD_BaseForm_OpenBaseFile						= new SOSMsgJOE("JOE_FD_BaseForm_OpenBaseFile");

	// JobMainComposite
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobMainComposite_MainOptions					= new SOSMsgJOE("JOE_G_JobMainComposite_MainOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainComposite_JobName						= new SOSMsgJOE("JOE_L_JobMainComposite_JobName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainComposite_JobName						= new SOSMsgJOE("JOE_T_JobMainComposite_JobName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainComposite_JobTitle						= new SOSMsgJOE("JOE_L_JobMainComposite_JobTitle");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainComposite_JobTitle						= new SOSMsgJOE("JOE_T_JobMainComposite_JobTitle");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainComposite_ProcessClass					= new SOSMsgJOE("JOE_L_JobMainComposite_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobMainComposite_ShowProcessClass				= new SOSMsgJOE("JOE_B_JobMainComposite_ShowProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobMainComposite_ProcessClass				= new SOSMsgJOE("JOE_Cbo_JobMainComposite_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobMainComposite_BrowseProcessClass			= new SOSMsgJOE("JOE_B_JobMainComposite_BrowseProcessClass");

	// PreProcessingComposite
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_PreProcessingComposite_Script					= new SOSMsgJOE("JOE_G_PreProcessingComposite_Script");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_PreProcessingComposite_NameOrdering			= new SOSMsgJOE("JOE_Cmp_PreProcessingComposite_NameOrdering");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_PreProcessingComposite_PreProcessingName		= new SOSMsgJOE("JOE_L_PreProcessingComposite_PreProcessingName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_PreProcessingComposite_PreProcessingName		= new SOSMsgJOE("JOE_T_PreProcessingComposite_PreProcessingName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_PreProcessingComposite_Ordering				= new SOSMsgJOE("JOE_L_PreProcessingComposite_Ordering");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sp_PreProcessingComposite_Ordering				= new SOSMsgJOE("JOE_Sp_PreProcessingComposite_Ordering");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_PreProcessingComposite_Favourites				= new SOSMsgJOE("JOE_B_PreProcessingComposite_Favourites");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_PreProcessingComposite_Favourites			= new SOSMsgJOE("JOE_Cbo_PreProcessingComposite_Favourites");

	// ProcessClassesForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ProcessClassesForm_ProcessClasses				= new SOSMsgJOE("JOE_G_ProcessClassesForm_ProcessClasses");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ProcessClassesForm_ProcessClass				= new SOSMsgJOE("JOE_L_ProcessClassesForm_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ProcessClassesForm_ProcessClass				= new SOSMsgJOE("JOE_T_ProcessClassesForm_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ProcessClassesForm_Apply						= new SOSMsgJOE("JOE_B_ProcessClassesForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ProcessClassesForm_MaxProcesses				= new SOSMsgJOE("JOE_L_ProcessClassesForm_MaxProcesses");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ProcessClassesForm_MaxProcesses				= new SOSMsgJOE("JOE_T_ProcessClassesForm_MaxProcesses");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ProcessClassesForm_remoteExecution			= new SOSMsgJOE("JOE_L_ProcessClassesForm_remoteExecution");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ProcessClassesForm_remoteExecution			= new SOSMsgJOE("JOE_T_ProcessClassesForm_remoteExecution");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ProcessClassesForm_Port						= new SOSMsgJOE("JOE_L_ProcessClassesForm_Port");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ProcessClassesForm_Port						= new SOSMsgJOE("JOE_T_ProcessClassesForm_Port");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_ProcessClassesForm_S1						= new SOSMsgJOE("JOE_Sep_ProcessClassesForm_S1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ProcessClassesForm_NewProcessClass			= new SOSMsgJOE("JOE_B_ProcessClassesForm_NewProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_ProcessClassesForm_S2						= new SOSMsgJOE("JOE_Sep_ProcessClassesForm_S2");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ProcessClassesForm_RemoveProcessClass			= new SOSMsgJOE("JOE_B_ProcessClassesForm_RemoveProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_ProcessClassesForm_ProcessClasses			= new SOSMsgJOE("JOE_Tbl_ProcessClassesForm_ProcessClasses");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_ProcessClassesForm_ProcessClass				= new SOSMsgJOE("JOE_TCl_ProcessClassesForm_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_ProcessClassesForm_MaxProcesses				= new SOSMsgJOE("JOE_TCl_ProcessClassesForm_MaxProcesses");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_ProcessClassesForm_RemoteExecution			= new SOSMsgJOE("JOE_TCl_ProcessClassesForm_RemoteExecution");

	// ClusterForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ClusterForm_Cluster							= new SOSMsgJOE("JOE_G_ClusterForm_Cluster");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ClusterForm_HeartbeatTimeout					= new SOSMsgJOE("JOE_L_ClusterForm_HeartbeatTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ClusterForm_HeartbeatOwnTimeout				= new SOSMsgJOE("JOE_L_ClusterForm_HeartbeatOwnTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ClusterForm_HeartbeatOwnTimeout				= new SOSMsgJOE("JOE_T_ClusterForm_HeartbeatOwnTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ClusterForm_HeartbeatWarnTimeout				= new SOSMsgJOE("JOE_L_ClusterForm_HeartbeatWarnTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ClusterForm_HeartbeatWarnTimeout				= new SOSMsgJOE("JOE_T_ClusterForm_HeartbeatWarnTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ClusterForm_HeartbeatTimeout					= new SOSMsgJOE("JOE_T_ClusterForm_HeartbeatTimeout");

	// CommandsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_CommandsForm_Commands							= new SOSMsgJOE("JOE_G_CommandsForm_Commands");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_CommandsForm_Commands							= new SOSMsgJOE("JOE_T_CommandsForm_Commands");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_CommandsForm_Apply							= new SOSMsgJOE("JOE_B_CommandsForm_Apply");

	// TODO ConfigForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Config								= new SOSMsgJOE("JOE_G_ConfigForm_Config");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Group1								= new SOSMsgJOE("JOE_G_ConfigForm_Group1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_SchedulerID						= new SOSMsgJOE("JOE_L_ConfigForm_SchedulerID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_SchedulerID						= new SOSMsgJOE("JOE_T_ConfigForm_SchedulerID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_Params								= new SOSMsgJOE("JOE_L_ConfigForm_Params");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_Params								= new SOSMsgJOE("JOE_T_ConfigForm_Params");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_IncludePath						= new SOSMsgJOE("JOE_L_ConfigForm_IncludePath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_IncludePath						= new SOSMsgJOE("JOE_T_ConfigForm_IncludePath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_IPAddress							= new SOSMsgJOE("JOE_L_ConfigForm_IPAddress");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_IPAddress							= new SOSMsgJOE("JOE_T_ConfigForm_IPAddress");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_LogDir								= new SOSMsgJOE("JOE_L_ConfigForm_LogDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_LogDir								= new SOSMsgJOE("JOE_T_ConfigForm_LogDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_MailXSLT							= new SOSMsgJOE("JOE_L_ConfigForm_MailXSLT");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_MailXSLT							= new SOSMsgJOE("JOE_T_ConfigForm_MailXSLT");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_CentralConfigDir					= new SOSMsgJOE("JOE_L_ConfigForm_CentralConfigDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_CentralConfigDir					= new SOSMsgJOE("JOE_T_ConfigForm_CentralConfigDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Event								= new SOSMsgJOE("JOE_G_ConfigForm_Event");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_ConfigAddEvent						= new SOSMsgJOE("JOE_L_ConfigForm_ConfigAddEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_ConfigForm_ConfigAddEvent					= new SOSMsgJOE("JOE_Cbo_ConfigForm_ConfigAddEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ConfigForm_Browse1							= new SOSMsgJOE("JOE_B_ConfigForm_Browse1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ConfigForm_Browse2							= new SOSMsgJOE("JOE_B_ConfigForm_Browse2");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ConfigForm_Browse3							= new SOSMsgJOE("JOE_B_ConfigForm_Browse3");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_ConfigModifyEvent					= new SOSMsgJOE("JOE_L_ConfigForm_ConfigModifyEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_ConfigForm_ConfigModifyEvent				= new SOSMsgJOE("JOE_Cbo_ConfigForm_ConfigModifyEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_ConfigDeleteEvent					= new SOSMsgJOE("JOE_L_ConfigForm_ConfigDeleteEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_ConfigForm_ConfigDeleteEvent				= new SOSMsgJOE("JOE_Cbo_ConfigForm_ConfigDeleteEvent");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_ConfigForm_CmpPort							= new SOSMsgJOE("JOE_Cmp_ConfigForm_CmpPort");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Ports								= new SOSMsgJOE("JOE_G_ConfigForm_Ports");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ConfigForm_SamePortsCheckBtn					= new SOSMsgJOE("JOE_B_ConfigForm_SamePortsCheckBtn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_SchedulerPort						= new SOSMsgJOE("JOE_L_ConfigForm_SchedulerPort");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_SamePort							= new SOSMsgJOE("JOE_T_ConfigForm_SamePort");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_TCP								= new SOSMsgJOE("JOE_L_ConfigForm_TCP");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_TCP								= new SOSMsgJOE("JOE_T_ConfigForm_TCP");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_UDP								= new SOSMsgJOE("JOE_L_ConfigForm_UDP");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_UDP								= new SOSMsgJOE("JOE_T_ConfigForm_UDP");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Supervisor							= new SOSMsgJOE("JOE_G_ConfigForm_Supervisor");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_Host								= new SOSMsgJOE("JOE_L_ConfigForm_Host");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_Host								= new SOSMsgJOE("JOE_T_ConfigForm_Host");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_SupervisorPort						= new SOSMsgJOE("JOE_L_ConfigForm_SupervisorPort");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_SupervisorPort						= new SOSMsgJOE("JOE_T_ConfigForm_SupervisorPort");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_JavaOptions						= new SOSMsgJOE("JOE_G_ConfigForm_JavaOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_ClassPath							= new SOSMsgJOE("JOE_L_ConfigForm_ClassPath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_ClassPath							= new SOSMsgJOE("JOE_T_ConfigForm_ClassPath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_ConfigForm_Options							= new SOSMsgJOE("JOE_L_ConfigForm_Options");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_Options							= new SOSMsgJOE("JOE_T_ConfigForm_Options");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_ConfigForm_Comment							= new SOSMsgJOE("JOE_G_ConfigForm_Comment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_ConfigForm_Comment							= new SOSMsgJOE("JOE_B_ConfigForm_Comment");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_ConfigForm_Comment							= new SOSMsgJOE("JOE_T_ConfigForm_Comment");

	// TODO DateForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DateForm_Holiday								= new SOSMsgJOE("JOE_G_DateForm_Holiday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DateForm_Specific								= new SOSMsgJOE("JOE_G_DateForm_Specific");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DateForm_Dates								= new SOSMsgJOE("JOE_G_DateForm_Dates");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DateForm_Year									= new SOSMsgJOE("JOE_L_DateForm_Year");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sp_DateForm_Year								= new SOSMsgJOE("JOE_Sp_DateForm_Year");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DateForm_Month								= new SOSMsgJOE("JOE_L_DateForm_Month");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sp_DateForm_Month								= new SOSMsgJOE("JOE_Sp_DateForm_Month");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DateForm_Day									= new SOSMsgJOE("JOE_L_DateForm_Day");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sp_DateForm_Day									= new SOSMsgJOE("JOE_Sp_DateForm_Day");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_AddDate								= new SOSMsgJOE("JOE_B_DateForm_AddDate");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0015											= new SOSMsgJOE("JOE_M_0015");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0014											= new SOSMsgJOE("JOE_M_0014");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_DateForm_S1									= new SOSMsgJOE("JOE_Sep_DateForm_S1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Lst_DateForm_DatesList							= new SOSMsgJOE("JOE_Lst_DateForm_DatesList");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_RemoveDate							= new SOSMsgJOE("JOE_B_DateForm_RemoveDate");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DateForm_IncludeFiles							= new SOSMsgJOE("JOE_G_DateForm_IncludeFiles");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_IsLifeFile							= new SOSMsgJOE("JOE_B_DateForm_IsLifeFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_DateForm_Include							= new SOSMsgJOE("JOE_Cbo_DateForm_Include");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_AddFile								= new SOSMsgJOE("JOE_B_DateForm_AddFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_DateForm_S2									= new SOSMsgJOE("JOE_Sep_DateForm_S2");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_DateForm_Includes							= new SOSMsgJOE("JOE_Tbl_DateForm_Includes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DateForm_NameColumn							= new SOSMsgJOE("JOE_TCl_DateForm_NameColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DateForm_FileColumn							= new SOSMsgJOE("JOE_TCl_DateForm_FileColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DateForm_DescriptionColumn					= new SOSMsgJOE("JOE_TCl_DateForm_DescriptionColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_NewButton							= new SOSMsgJOE("JOE_B_DateForm_NewButton");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_OpenButton							= new SOSMsgJOE("JOE_B_DateForm_OpenButton");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DateForm_RemoveFile							= new SOSMsgJOE("JOE_B_DateForm_RemoveFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0001											= new SOSMsgJOE("JOE_E_0001");

	// DaysForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DaysForm_WeekdaysGroup						= new SOSMsgJOE("JOE_G_DaysForm_WeekdaysGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DaysForm_MonthdaysGroup						= new SOSMsgJOE("JOE_G_DaysForm_MonthdaysGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DaysForm_UltimosGroup							= new SOSMsgJOE("JOE_G_DaysForm_UltimosGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DaysForm_MonthGroup							= new SOSMsgJOE("JOE_G_DaysForm_MonthGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DaysForm_Weekday								= new SOSMsgJOE("JOE_L_DaysForm_Weekday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DaysForm_Monthday								= new SOSMsgJOE("JOE_L_DaysForm_Monthday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DaysForm_Ultimo								= new SOSMsgJOE("JOE_L_DaysForm_Ultimo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DaysForm_Month								= new SOSMsgJOE("JOE_L_DaysForm_Month");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_AddWeekday							= new SOSMsgJOE("JOE_B_DaysForm_AddWeekday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_AddMonthday							= new SOSMsgJOE("JOE_B_DaysForm_AddMonthday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_AddUltimo							= new SOSMsgJOE("JOE_B_DaysForm_AddUltimo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_AddMonth								= new SOSMsgJOE("JOE_B_DaysForm_AddMonth");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sep_DaysForm_S1									= new SOSMsgJOE("JOE_Sep_DaysForm_S1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Lst_DaysForm_UsedDays							= new SOSMsgJOE("JOE_Lst_DaysForm_UsedDays");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_Remove								= new SOSMsgJOE("JOE_B_DaysForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Lst_DaysForm_DaysList							= new SOSMsgJOE("JOE_Lst_DaysForm_DaysList");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Lst_DaysForm_GroupsList							= new SOSMsgJOE("JOE_Lst_DaysForm_GroupsList");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_NewGroup								= new SOSMsgJOE("JOE_B_DaysForm_NewGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_Add									= new SOSMsgJOE("JOE_B_DaysForm_Add");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_ApplyGroup							= new SOSMsgJOE("JOE_B_DaysForm_ApplyGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DaysForm_RemoveDay							= new SOSMsgJOE("JOE_B_DaysForm_RemoveDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_Monthnames									= new SOSMsgJOE("JOE_L_Monthnames");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_DaysForm_UnusedDays							= new SOSMsgJOE("JOE_Cbo_DaysForm_UnusedDays");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0008											= new SOSMsgJOE("JOE_E_0008");

	// TODO DetailDialogForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0017											= new SOSMsgJOE("JOE_M_0017");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0018											= new SOSMsgJOE("JOE_M_0018");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0019											= new SOSMsgJOE("JOE_M_0019");

	// DetailForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DetailForm_MainGroup							= new SOSMsgJOE("JOE_G_DetailForm_MainGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DetailForm_ParameterGroup						= new SOSMsgJOE("JOE_G_DetailForm_ParameterGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_NameLabel										= new SOSMsgJOE("JOE_L_NameLabel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_Name								= new SOSMsgJOE("JOE_T_DetailForm_Name");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_Value											= new SOSMsgJOE("JOE_L_Value");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_Value								= new SOSMsgJOE("JOE_T_DetailForm_Value");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Text								= new SOSMsgJOE("JOE_B_DetailForm_Text");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_ApplyParam							= new SOSMsgJOE("JOE_B_DetailForm_ApplyParam");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_DetailForm_Params							= new SOSMsgJOE("JOE_Tbl_DetailForm_Params");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DetailForm_NameColumn						= new SOSMsgJOE("JOE_TCl_DetailForm_NameColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DetailForm_ValueColumn						= new SOSMsgJOE("JOE_TCl_DetailForm_ValueColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_DetailForm_TextColumn						= new SOSMsgJOE("JOE_TCl_DetailForm_TextColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_New								= new SOSMsgJOE("JOE_B_DetailForm_New");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Up									= new SOSMsgJOE("JOE_B_DetailForm_Up");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Down								= new SOSMsgJOE("JOE_B_DetailForm_Down");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Wizard								= new SOSMsgJOE("JOE_B_DetailForm_Wizard");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Remove								= new SOSMsgJOE("JOE_B_DetailForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_TempDocumentation					= new SOSMsgJOE("JOE_B_DetailForm_TempDocumentation");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_ApplyDetails						= new SOSMsgJOE("JOE_B_DetailForm_ApplyDetails");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Cancel								= new SOSMsgJOE("JOE_B_DetailForm_Cancel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_JobChainNote						= new SOSMsgJOE("JOE_T_DetailForm_Note");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_DetailForm_Language							= new SOSMsgJOE("JOE_Cbo_DetailForm_Language");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_RefreshWizardNoteParam				= new SOSMsgJOE("JOE_B_DetailForm_RefreshWizardNoteParam");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_Param								= new SOSMsgJOE("JOE_T_DetailForm_Param");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DetailForm_NoteGroup							= new SOSMsgJOE("JOE_G_DetailForm_NoteGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_Note								= new SOSMsgJOE("JOE_T_DetailForm_JobChainNote");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_XML								= new SOSMsgJOE("JOE_B_DetailForm_XML");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailForm_Documentation						= new SOSMsgJOE("JOE_B_DetailForm_Documentation");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0013											= new SOSMsgJOE("JOE_M_0013");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0012											= new SOSMsgJOE("JOE_M_0012");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0011											= new SOSMsgJOE("JOE_M_0011");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DetailForm_JobDocumentation					= new SOSMsgJOE("JOE_L_DetailForm_JobDocumentation");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailForm_ParamsFile							= new SOSMsgJOE("JOE_T_DetailForm_ParamsFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_DetailForm_ConfigFile							= new SOSMsgJOE("JOE_L_DetailForm_ConfigFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0010											= new SOSMsgJOE("JOE_M_0010");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0020											= new SOSMsgJOE("JOE_M_0020");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0021											= new SOSMsgJOE("JOE_M_0021");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0022											= new SOSMsgJOE("JOE_M_0022");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0023											= new SOSMsgJOE("JOE_M_0023");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0024											= new SOSMsgJOE("JOE_M_0024");

	// DetailXMLEditorDialogForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0009											= new SOSMsgJOE("JOE_M_0009");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_DetailXMLEditorDialogForm_JobGroup			= new SOSMsgJOE("JOE_G_DetailXMLEditorDialogForm_JobGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_DetailXMLEditorDialogForm_XML					= new SOSMsgJOE("JOE_T_DetailXMLEditorDialogForm_XML");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailXMLEditorDialogForm_Apply				= new SOSMsgJOE("JOE_B_DetailXMLEditorDialogForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_DetailXMLEditorDialogForm_Close				= new SOSMsgJOE("JOE_B_DetailXMLEditorDialogForm_Close");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0002											= new SOSMsgJOE("JOE_E_0002");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0008											= new SOSMsgJOE("JOE_M_0008");

	// HotFolderDialog
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_HotFolderDialog_SchedulerGroup				= new SOSMsgJOE("JOE_M_HotFolderDialog_SchedulerGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0006											= new SOSMsgJOE("JOE_M_0006");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0007											= new SOSMsgJOE("JOE_M_0007");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HotFolderDialog_Cancel						= new SOSMsgJOE("JOE_B_HotFolderDialog_Cancel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HotFolderDialog_Name							= new SOSMsgJOE("JOE_T_HotFolderDialog_Name");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HotFolderDialog_Port							= new SOSMsgJOE("JOE_T_HotFolderDialog_Port");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HotFolderDialog_Add							= new SOSMsgJOE("JOE_B_HotFolderDialog_Add");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HotFolderDialog_Rename						= new SOSMsgJOE("JOE_B_HotFolderDialog_Rename");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0005											= new SOSMsgJOE("JOE_M_0005");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0007											= new SOSMsgJOE("JOE_E_0007");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HotFolderDialog_Open							= new SOSMsgJOE("JOE_B_HotFolderDialog_Open");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0003											= new SOSMsgJOE("JOE_E_0003");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0004											= new SOSMsgJOE("JOE_E_0004");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0005											= new SOSMsgJOE("JOE_E_0005");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0001											= new SOSMsgJOE("JOE_M_0001");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0002											= new SOSMsgJOE("JOE_M_0002");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0003											= new SOSMsgJOE("JOE_M_0003");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0004											= new SOSMsgJOE("JOE_M_0004");
	@I18NMsg
	public static final SOSMsgJOE	JOE_HotFolderDialog_Tree							= new SOSMsgJOE("JOE_HotFolderDialog_Tree");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0006											= new SOSMsgJOE("JOE_E_0006");

	// HttpAuthenticationForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_HttpAuthenticationForm_AuthGroup				= new SOSMsgJOE("JOE_G_HttpAuthenticationForm_AuthGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_HttpAuthenticationForm_Group					= new SOSMsgJOE("JOE_G_HttpAuthenticationForm_Group");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_HttpAuthenticationForm_UserName				= new SOSMsgJOE("JOE_L_HttpAuthenticationForm_UserName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HttpAuthenticationForm_UserName				= new SOSMsgJOE("JOE_T_HttpAuthenticationForm_UserName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_HttpAuthenticationForm_Password				= new SOSMsgJOE("JOE_L_HttpAuthenticationForm_Password");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HttpAuthenticationForm_Password				= new SOSMsgJOE("JOE_T_HttpAuthenticationForm_Password");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HttpAuthenticationForm_Encrypt				= new SOSMsgJOE("JOE_B_HttpAuthenticationForm_Encrypt");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_HttpAuthenticationForm_MD5PW					= new SOSMsgJOE("JOE_L_HttpAuthenticationForm_MD5PW");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HttpAuthenticationForm_MD5PW					= new SOSMsgJOE("JOE_T_HttpAuthenticationForm_MD5PW");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HttpAuthenticationForm_Apply					= new SOSMsgJOE("JOE_B_HttpAuthenticationForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_HttpAuthenticationForm_Users				= new SOSMsgJOE("JOE_Tbl_HttpAuthenticationForm_Users");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_HttpAuthenticationForm_NameColumn			= new SOSMsgJOE("JOE_TCl_HttpAuthenticationForm_NameColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_HttpAuthenticationForm_PWColumn				= new SOSMsgJOE("JOE_TCl_HttpAuthenticationForm_PWColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HttpAuthenticationForm_Remove					= new SOSMsgJOE("JOE_B_HttpAuthenticationForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0016											= new SOSMsgJOE("JOE_M_0016");

	// HttpDirectoriesForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_HttpDirectoriesForm_DirectoriesGroup			= new SOSMsgJOE("JOE_G_HttpDirectoriesForm_DirectoriesGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_HttpDirectoriesForm_Group1					= new SOSMsgJOE("JOE_G_HttpDirectoriesForm_Group1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_HttpDirectoriesForm_URLPath					= new SOSMsgJOE("JOE_L_HttpDirectoriesForm_URLPath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HttpDirectoriesForm_URLPath					= new SOSMsgJOE("JOE_T_HttpDirectoriesForm_URLPath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_HttpDirectoriesForm_Path						= new SOSMsgJOE("JOE_L_HttpDirectoriesForm_Path");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_HttpDirectoriesForm_Path						= new SOSMsgJOE("JOE_T_HttpDirectoriesForm_Path");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HttpDirectoriesForm_Apply						= new SOSMsgJOE("JOE_B_HttpDirectoriesForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_HttpDirectoriesForm_DirectoriesTable		= new SOSMsgJOE("JOE_Tbl_HttpDirectoriesForm_DirectoriesTable");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_HttpDirectoriesForm_URLPath					= new SOSMsgJOE("JOE_TCl_HttpDirectoriesForm_URLPath");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_HttpDirectoriesForm_Path					= new SOSMsgJOE("JOE_TCl_HttpDirectoriesForm_Path");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_HttpDirectoriesForm_Remove					= new SOSMsgJOE("JOE_B_HttpDirectoriesForm_Remove");

	// JobAssistentDelayAfterErrorForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step8of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step8of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step8of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step8of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_DelayAfterError					= new SOSMsgJOE("JOE_M_JobAssistent_DelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobGroup							= new SOSMsgJOE("JOE_M_JobAssistent_JobGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_JobWait							= new SOSMsgJOE("JOE_L_JobAssistent_JobWait");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_JobAssistent_Time							= new SOSMsgJOE("JOE_Cmp_JobAssistent_Time");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Hour								= new SOSMsgJOE("JOE_T_JobAssistent_Hour");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Min								= new SOSMsgJOE("JOE_T_JobAssistent_Min");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Sec								= new SOSMsgJOE("JOE_T_JobAssistent_Sec");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_TimeFormat						= new SOSMsgJOE("JOE_L_JobAssistent_TimeFormat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_OftenSetBack						= new SOSMsgJOE("JOE_L_JobAssistent_OftenSetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_ErrorCount						= new SOSMsgJOE("JOE_T_JobAssistent_ErrorCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_MaxErrors						= new SOSMsgJOE("JOE_L_JobAssistent_MaxErrors");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_MaxErrors						= new SOSMsgJOE("JOE_T_JobAssistent_MaxErrors");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_JobAssistent_Cancel							= new SOSMsgJOE("JOE_Cmp_JobAssistent_Cancel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Cancel							= new SOSMsgJOE("JOE_B_JobAssistent_Cancel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cmp_JobAssistent_Show							= new SOSMsgJOE("JOE_Cmp_JobAssistent_Show");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Show								= new SOSMsgJOE("JOE_B_JobAssistent_Show");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Finish							= new SOSMsgJOE("JOE_B_JobAssistent_Finish");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Back								= new SOSMsgJOE("JOE_B_JobAssistent_Back");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Next								= new SOSMsgJOE("JOE_B_JobAssistent_Next");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoNum							= new SOSMsgJOE("JOE_M_JobAssistent_NoNum");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Finish							= new SOSMsgJOE("JOE_M_JobAssistent_Finish");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_EndWizard						= new SOSMsgJOE("JOE_M_JobAssistent_EndWizard");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_TimeMissing						= new SOSMsgJOE("JOE_M_JobAssistent_TimeMissing");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_ErrorCountMissing				= new SOSMsgJOE("JOE_M_JobAssistent_ErrorCountMissing");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_CancelWizard						= new SOSMsgJOE("JOE_M_JobAssistent_CancelWizard");

	// JobAssistentDelayOrderAfterSetbackForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step9of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step9of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_OrderAfterSetback				= new SOSMsgJOE("JOE_M_JobAssistent_OrderAfterSetback");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_OrderWait						= new SOSMsgJOE("JOE_L_JobAssistent_OrderWait");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_OftenSetback						= new SOSMsgJOE("JOE_L_JobAssistent_OftenSetback");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_SetBack							= new SOSMsgJOE("JOE_T_JobAssistent_SetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_NumOfSetback						= new SOSMsgJOE("JOE_L_JobAssistent_NumOfSetback");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_No								= new SOSMsgJOE("JOE_B_JobAssistent_No");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Yes								= new SOSMsgJOE("JOE_B_JobAssistent_Yes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Composite1										= new SOSMsgJOE("JOE_Composite1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Composite2										= new SOSMsgJOE("JOE_Composite2");

	// JobAssistentExecuteForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Execute							= new SOSMsgJOE("JOE_M_JobAssistent_Execute");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Process							= new SOSMsgJOE("JOE_B_JobAssistent_Process");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Script							= new SOSMsgJOE("JOE_B_JobAssistent_Script");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Language							= new SOSMsgJOE("JOE_L_JobAssistent_Language");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobAssistent_Language						= new SOSMsgJOE("JOE_Cbo_JobAssistent_Language");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Include							= new SOSMsgJOE("JOE_T_JobAssistent_Include");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Include							= new SOSMsgJOE("JOE_L_JobAssistent_Include");

	// JobAssistentForm

	// JobAssistentImportJobParamsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0025											= new SOSMsgJOE("JOE_M_0025");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0026											= new SOSMsgJOE("JOE_M_0026");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step3of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step3of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step3of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step3of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobParameter						= new SOSMsgJOE("JOE_M_JobAssistent_JobParameter");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobParameter_Missing_Xulrunner	= new SOSMsgJOE("JOE_M_JobAssistent_JobParameter_Missing_Xulrunner");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobAssistent_ParamGroup						= new SOSMsgJOE("JOE_G_JobAssistent_ParamGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Composite3										= new SOSMsgJOE("JOE_Composite3");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Name								= new SOSMsgJOE("JOE_T_JobAssistent_Name");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Value							= new SOSMsgJOE("JOE_T_JobAssistent_Value");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Apply							= new SOSMsgJOE("JOE_B_JobAssistent_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobAssistent_DescParams						= new SOSMsgJOE("JOE_Tbl_JobAssistent_DescParams");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobAssistent_NameColumn						= new SOSMsgJOE("JOE_TCl_JobAssistent_NameColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobAssistent_ValueColumn					= new SOSMsgJOE("JOE_TCl_JobAssistent_ValueColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Composite4										= new SOSMsgJOE("JOE_Composite4");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Put								= new SOSMsgJOE("JOE_B_JobAssistent_Put");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_PutAll							= new SOSMsgJOE("JOE_B_JobAssistent_PutAll");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Remove							= new SOSMsgJOE("JOE_B_JobAssistent_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_RemoveAll						= new SOSMsgJOE("JOE_B_JobAssistent_RemoveAll");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobAssistent_SelectedParams					= new SOSMsgJOE("JOE_Tbl_JobAssistent_SelectedParams");
	@I18NMsg
	public static final SOSMsgJOE	JOE_DescriptionBrowser								= new SOSMsgJOE("JOE_DescriptionBrowser");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoParamName						= new SOSMsgJOE("JOE_M_JobAssistent_NoParamName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_ParamsExist						= new SOSMsgJOE("JOE_M_JobAssistent_ParamsExist");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoParamsSelected					= new SOSMsgJOE("JOE_M_JobAssistent_NoParamsSelected");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoRemParamsSelected				= new SOSMsgJOE("JOE_M_JobAssistent_NoRemParamsSelected");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_ParamsRequired					= new SOSMsgJOE("JOE_M_JobAssistent_ParamsRequired");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_ApplyChanges						= new SOSMsgJOE("JOE_M_ApplyChanges");

	// JobAssistentImportJobsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_MissingDirectory					= new SOSMsgJOE("JOE_M_JobAssistent_MissingDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step2of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step2of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step2of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step2of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_ImportJobs						= new SOSMsgJOE("JOE_M_JobAssistent_ImportJobs");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobAssistent_JobGroup							= new SOSMsgJOE("JOE_G_JobAssistent_JobGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_JobName							= new SOSMsgJOE("JOE_L_JobAssistent_JobName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_JobName							= new SOSMsgJOE("JOE_T_JobAssistent_JobName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Title							= new SOSMsgJOE("JOE_L_JobAssistent_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Title							= new SOSMsgJOE("JOE_T_JobAssistent_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_PathLabel						= new SOSMsgJOE("JOE_L_JobAssistent_PathLabel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Path								= new SOSMsgJOE("JOE_T_JobAssistent_Path");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Description						= new SOSMsgJOE("JOE_B_JobAssistent_Description");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoJobDescription					= new SOSMsgJOE("JOE_M_JobAssistent_NoJobDescription");
	@I18NMsg
	public static final SOSMsgJOE	JOE_E_0009											= new SOSMsgJOE("JOE_E_0009");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Import							= new SOSMsgJOE("JOE_B_JobAssistent_Import");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobNameExists					= new SOSMsgJOE("JOE_M_JobAssistent_JobNameExists");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_DiscardChanges					= new SOSMsgJOE("JOE_M_JobAssistent_DiscardChanges");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_ImportParams						= new SOSMsgJOE("JOE_M_JobAssistent_ImportParams");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobAssistent_JobsGroup						= new SOSMsgJOE("JOE_G_JobAssistent_JobsGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_JobTree							= new SOSMsgJOE("JOE_JobAssistent_JobTree");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_NameTreeColumn						= new SOSMsgJOE("JOE_JobAssistent_NameTreeColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_TitleTreeColumn					= new SOSMsgJOE("JOE_JobAssistent_TitleTreeColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_FilenameTreeColumn					= new SOSMsgJOE("JOE_JobAssistent_FilenameTreeColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoJobDoc							= new SOSMsgJOE("JOE_M_JobAssistent_NoJobDoc");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoJobSelected					= new SOSMsgJOE("JOE_M_JobAssistent_NoJobSelected");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoJobName						= new SOSMsgJOE("JOE_M_JobAssistent_NoJobName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_EditJobName						= new SOSMsgJOE("JOE_M_JobAssistent_EditJobName");

	// JobAssistentInfoForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobWizard						= new SOSMsgJOE("JOE_M_JobAssistent_JobWizard");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_InfoGlobal						= new SOSMsgJOE("JOE_T_JobAssistent_InfoGlobal");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_ShowInFuture						= new SOSMsgJOE("JOE_B_JobAssistent_ShowInFuture");

	// JobAssistentMonitoringDirectoryForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_MonitoringDirectory				= new SOSMsgJOE("JOE_M_JobAssistent_MonitoringDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Directory						= new SOSMsgJOE("JOE_L_JobAssistent_Directory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Directory						= new SOSMsgJOE("JOE_T_JobAssistent_Directory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_ApplyDir							= new SOSMsgJOE("JOE_B_JobAssistent_ApplyDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_NewDirectory						= new SOSMsgJOE("JOE_B_JobAssistent_NewDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Regex							= new SOSMsgJOE("JOE_L_JobAssistent_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Regex							= new SOSMsgJOE("JOE_T_JobAssistent_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobAssistent_WatchDirectory					= new SOSMsgJOE("JOE_Tbl_JobAssistent_WatchDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobAssistent_DirectoryColumn				= new SOSMsgJOE("JOE_TCl_JobAssistent_DirectoryColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobAssistent_RegexColumn					= new SOSMsgJOE("JOE_TCl_JobAssistent_RegexColumn");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_RemoveDirectory					= new SOSMsgJOE("JOE_B_JobAssistent_RemoveDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Close							= new SOSMsgJOE("JOE_B_JobAssistent_Close");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentMonitoringDirectoryForms_Apply	= new SOSMsgJOE("JOE_B_JobAssistentMonitoringDirectoryForms_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Close							= new SOSMsgJOE("JOE_M_JobAssistent_Close");

	// JobAssistentPeriodForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobAssistent_Period							= new SOSMsgJOE("JOE_G_JobAssistent_Period");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_BeginTime						= new SOSMsgJOE("JOE_L_JobAssistent_BeginTime");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_Colon											= new SOSMsgJOE("JOE_L_Colon");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_BeginHours						= new SOSMsgJOE("JOE_T_JobAssistent_BeginHours");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_BeginMinutes						= new SOSMsgJOE("JOE_T_JobAssistent_BeginMinutes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_BeginSeconds						= new SOSMsgJOE("JOE_T_JobAssistent_BeginSeconds");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_EndTime							= new SOSMsgJOE("JOE_L_JobAssistent_EndTime");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_EndHours							= new SOSMsgJOE("JOE_T_JobAssistent_EndHours");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_EndMinutes						= new SOSMsgJOE("JOE_T_JobAssistent_EndMinutes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_EndSeconds						= new SOSMsgJOE("JOE_T_JobAssistent_EndSeconds");

	// JobAssistentPeriodForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_RunTimePeriods					= new SOSMsgJOE("JOE_M_JobAssistent_RunTimePeriods");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_EveryDayTabItem					= new SOSMsgJOE("JOE_JobAssistent_EveryDayTabItem");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Group											= new SOSMsgJOE("JOE_Group");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_NewPeriod						= new SOSMsgJOE("JOE_B_JobAssistent_NewPeriod");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_WeekdayTabItem						= new SOSMsgJOE("JOE_JobAssistent_WeekdayTabItem");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobAssistent_Weekday						= new SOSMsgJOE("JOE_Cbo_JobAssistent_Weekday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_MonthDayTabItem					= new SOSMsgJOE("JOE_JobAssistent_MonthDayTabItem");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobAssistent_Month							= new SOSMsgJOE("JOE_Cbo_JobAssistent_Month");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_SpecificDayTabItem					= new SOSMsgJOE("JOE_JobAssistent_SpecificDayTabItem");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_AddPeriod						= new SOSMsgJOE("JOE_B_JobAssistent_AddPeriod");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_PeriodList							= new SOSMsgJOE("JOE_JobAssistent_PeriodList");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentPeriodForms_Remove				= new SOSMsgJOE("JOE_B_JobAssistentPeriodForms_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentPeriodForms_Apply					= new SOSMsgJOE("JOE_B_JobAssistentPeriodForms_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_EveryDay							= new SOSMsgJOE("JOE_M_JobAssistent_EveryDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0027											= new SOSMsgJOE("JOE_M_0027");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoWeekdaySelected				= new SOSMsgJOE("JOE_M_JobAssistent_NoWeekdaySelected");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0028											= new SOSMsgJOE("JOE_M_0028");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_SpecificDay						= new SOSMsgJOE("JOE_M_JobAssistent_SpecificDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Weekday							= new SOSMsgJOE("JOE_M_JobAssistent_Weekday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Monthday							= new SOSMsgJOE("JOE_M_JobAssistent_Monthday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_SpecificWeekday					= new SOSMsgJOE("JOE_M_JobAssistent_SpecificWeekday");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0029											= new SOSMsgJOE("JOE_M_0029");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0030											= new SOSMsgJOE("JOE_M_0030");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_At								= new SOSMsgJOE("JOE_M_JobAssistent_At");

	// JobAssistentProcessForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step5of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step5of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step5of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step5of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_FileLabel							= new SOSMsgJOE("JOE_JobAssistent_FileLabel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_FileText							= new SOSMsgJOE("JOE_JobAssistent_FileText");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_ParameterLabel						= new SOSMsgJOE("JOE_JobAssistent_ParameterLabel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_ParameterText						= new SOSMsgJOE("JOE_JobAssistent_ParameterText");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_LogFileLabel						= new SOSMsgJOE("JOE_JobAssistent_LogFileLabel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_LogFileText						= new SOSMsgJOE("JOE_JobAssistent_LogFileText");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistentProcessForms_Execute				= new SOSMsgJOE("JOE_M_JobAssistentProcessForms_Execute");

	// JobAssistentRunOptionsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step7of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step7of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step7of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step7of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_RunOptions						= new SOSMsgJOE("JOE_M_JobAssistent_RunOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_Period							= new SOSMsgJOE("JOE_B_JobAssistent_Period");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_RunTime							= new SOSMsgJOE("JOE_B_JobAssistent_RunTime");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_DirectoryMonitoring				= new SOSMsgJOE("JOE_B_JobAssistent_DirectoryMonitoring");

	// JobAssistentRunTimeForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_RunTimeSingleStarts				= new SOSMsgJOE("JOE_M_JobAssistent_RunTimeSingleStarts");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_EveryDay							= new SOSMsgJOE("JOE_B_JobAssistent_EveryDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_At								= new SOSMsgJOE("JOE_L_JobAssistent_At");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_SpecificDay						= new SOSMsgJOE("JOE_B_JobAssistent_SpecificDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_SpecificDayDateTime				= new SOSMsgJOE("JOE_JobAssistent_SpecificDayDateTime");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_WeekDay							= new SOSMsgJOE("JOE_B_JobAssistent_WeekDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobAssistent_WeekDayCombo					= new SOSMsgJOE("JOE_Cbo_JobAssistent_WeekDayCombo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_MonthDay							= new SOSMsgJOE("JOE_B_JobAssistent_MonthDay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobAssistent_MonthCombo						= new SOSMsgJOE("JOE_Cbo_JobAssistent_MonthCombo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentRunTimeForms_Add					= new SOSMsgJOE("JOE_B_JobAssistentRunTimeForms_Add");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentRunTimeForms_Remove				= new SOSMsgJOE("JOE_B_JobAssistentRunTimeForms_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistentRunTimeForms_Apply				= new SOSMsgJOE("JOE_B_JobAssistentRunTimeForms_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_PeriodExists						= new SOSMsgJOE("JOE_M_JobAssistent_PeriodExists");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0031											= new SOSMsgJOE("JOE_M_0031");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_NoTime							= new SOSMsgJOE("JOE_M_JobAssistent_NoTime");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_AtHour							= new SOSMsgJOE("JOE_T_JobAssistent_AtHour");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_AtMinute							= new SOSMsgJOE("JOE_T_JobAssistent_AtMinute");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_AtSecond							= new SOSMsgJOE("JOE_T_JobAssistent_AtSecond");

	// JobAssistentScriptForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Script							= new SOSMsgJOE("JOE_M_JobAssistent_Script");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Language							= new SOSMsgJOE("JOE_T_JobAssistent_Language");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_UnknownLanguage					= new SOSMsgJOE("JOE_M_JobAssistent_UnknownLanguage");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_ComClass							= new SOSMsgJOE("JOE_L_JobAssistent_ComClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_JavaClass						= new SOSMsgJOE("JOE_L_JobAssistent_JavaClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_JavaClass						= new SOSMsgJOE("JOE_T_JobAssistent_JavaClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_FileName							= new SOSMsgJOE("JOE_L_JobAssistent_FileName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Resource							= new SOSMsgJOE("JOE_L_JobAssistent_Resource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Resource							= new SOSMsgJOE("JOE_T_JobAssistent_Resource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobAssistent_Include						= new SOSMsgJOE("JOE_Tbl_JobAssistent_Include");

	// JobAssistentTaskForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step4of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step4of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step4of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step4of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Tasks							= new SOSMsgJOE("JOE_M_JobAssistent_Tasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Tasks							= new SOSMsgJOE("JOE_L_JobAssistent_Tasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Tasks							= new SOSMsgJOE("JOE_T_JobAssistent_Tasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_MinimumTasks						= new SOSMsgJOE("JOE_L_JobAssistent_MinimumTasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_MinimumTasks						= new SOSMsgJOE("JOE_T_JobAssistent_MinimumTasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_MinTasksTooLarge					= new SOSMsgJOE("JOE_M_JobAssistent_MinTasksTooLarge");

	// JobAssistentTimeoutForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step6of9							= new SOSMsgJOE("JOE_M_JobAssistent_Step6of9");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step6of8							= new SOSMsgJOE("JOE_M_JobAssistent_Step6of8");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Timeout							= new SOSMsgJOE("JOE_M_JobAssistent_Timeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_Timeout							= new SOSMsgJOE("JOE_L_JobAssistent_Timeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_Timeout							= new SOSMsgJOE("JOE_T_JobAssistent_Timeout");

	// JobAssistentTimeoutOrderForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_IdleTimeout						= new SOSMsgJOE("JOE_L_JobAssistent_IdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobAssistent_IdleTimeout						= new SOSMsgJOE("JOE_T_JobAssistent_IdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobAssistent_ForceIdleTimeout					= new SOSMsgJOE("JOE_L_JobAssistent_ForceIdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_NoButton							= new SOSMsgJOE("JOE_B_JobAssistent_NoButton");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_YesButton						= new SOSMsgJOE("JOE_B_JobAssistent_YesButton");

	// JobAssistentTypeForms
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobType							= new SOSMsgJOE("JOE_M_JobAssistent_JobType");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_Step1							= new SOSMsgJOE("JOE_M_JobAssistent_Step1");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_OrderJob							= new SOSMsgJOE("JOE_B_JobAssistent_OrderJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobAssistent_StandaloneJob					= new SOSMsgJOE("JOE_B_JobAssistent_StandaloneJob");

	// JobChainConfigurationForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobAssistent_JobChainConfiguration			= new SOSMsgJOE("JOE_G_JobAssistent_JobChainConfiguration");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_State							= new SOSMsgJOE("JOE_M_JobAssistent_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_JobAssistent_JobChain							= new SOSMsgJOE("JOE_JobAssistent_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_FD_JobAssistent_OpenFile						= new SOSMsgJOE("JOE_FD_JobAssistent_OpenFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_FileIsOpened						= new SOSMsgJOE("JOE_M_JobAssistent_FileIsOpened");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_FileNotFound						= new SOSMsgJOE("JOE_M_JobAssistent_FileNotFound");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_FileReadProtected				= new SOSMsgJOE("JOE_M_JobAssistent_FileReadProtected");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobAssistent_JobDetailsEditor					= new SOSMsgJOE("JOE_M_JobAssistent_JobDetailsEditor");

	// JobChainForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChainForm_JobChain							= new SOSMsgJOE("JOE_M_JobChainForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainForm_ChainName						= new SOSMsgJOE("JOE_L_JobChainForm_ChainName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobChainForm_ChainName						= new SOSMsgJOE("JOE_T_JobChainForm_ChainName");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainForm_Parameter						= new SOSMsgJOE("JOE_B_JobChainForm_Parameter");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChainForm_SaveChain						= new SOSMsgJOE("JOE_M_JobChainForm_SaveChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChainForm_ChainNameChanged					= new SOSMsgJOE("JOE_M_JobChainForm_ChainNameChanged");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainForm_Title							= new SOSMsgJOE("JOE_L_JobChainForm_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobChainForm_Title							= new SOSMsgJOE("JOE_T_JobChainForm_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainForm_MaxOrders						= new SOSMsgJOE("JOE_L_JobChainForm_MaxOrders");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobChainForm_MaxOrders						= new SOSMsgJOE("JOE_T_JobChainForm_MaxOrders");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainForm_Recoverable						= new SOSMsgJOE("JOE_B_JobChainForm_Recoverable");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainForm_Distributed						= new SOSMsgJOE("JOE_B_JobChainForm_Distributed");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainForm_Visible							= new SOSMsgJOE("JOE_B_JobChainForm_Visible");

	// JobChainNestedNodesForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNestedNodesForm_NestedNodes					= new SOSMsgJOE("JOE_M_JCNestedNodesForm_NestedNodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainNodes_State						= new SOSMsgJOE("JOE_L_JobChainNodes_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobChainNodes_State						= new SOSMsgJOE("JOE_T_JobChainNodes_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainNodes_ApplyNode					= new SOSMsgJOE("JOE_B_JobChainNodes_ApplyNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNestedNodesForm_JobChain					= new SOSMsgJOE("JOE_L_JCNestedNodesForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainNodes_Goto						= new SOSMsgJOE("JOE_B_JobChainNodes_Goto");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JCNestedNodesForm_JobChain					= new SOSMsgJOE("JOE_Cbo_JCNestedNodesForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainNodes_Browse						= new SOSMsgJOE("JOE_B_JobChainNodes_Browse");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainNodes_NextState					= new SOSMsgJOE("JOE_L_JobChainNodes_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobChainNodes_NextState					= new SOSMsgJOE("JOE_Cbo_JobChainNodes_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_NewNode						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_NewNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobChainNodes_ErrorState					= new SOSMsgJOE("JOE_L_JobChainNodes_ErrorState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobChainNodes_ErrorState				= new SOSMsgJOE("JOE_Cbo_JobChainNodes_ErrorState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_Insert						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_Insert");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_FullNode					= new SOSMsgJOE("JOE_B_JCNestedNodesForm_FullNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_EndNode						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_EndNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JCNestedNodesForm_Nodes						= new SOSMsgJOE("JOE_Tbl_JCNestedNodesForm_Nodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_State						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_Node						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_Node");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_JobChain						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_NextState						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_ErrorState						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_ErrorState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNestedNodesForm_OnError						= new SOSMsgJOE("JOE_TCl_JCNestedNodesForm_OnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_Up						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_Up");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_Down						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_Down");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_Reorder						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_Reorder");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_AddMissingNodes						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_AddMissingNodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNestedNodesForm_RemoveNode						= new SOSMsgJOE("JOE_B_JCNestedNodesForm_RemoveNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNestedNodesForm_RemoveNode						= new SOSMsgJOE("JOE_M_JCNestedNodesForm_RemoveNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChain_StateAlreadyDefined						= new SOSMsgJOE("JOE_M_JobChain_StateAlreadyDefined");

//	JobChainNodesForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNodesForm_NodesGroup						= new SOSMsgJOE("JOE_M_JCNodesForm_NodesGroup");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Job						= new SOSMsgJOE("JOE_L_JCNodesForm_Job");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JCNodesForm_Job						= new SOSMsgJOE("JOE_Cbo_JCNodesForm_Job");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Delay						= new SOSMsgJOE("JOE_L_JCNodesForm_Delay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_Delay						= new SOSMsgJOE("JOE_T_JCNodesForm_Delay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_ImportJob						= new SOSMsgJOE("JOE_B_JCNodesForm_ImportJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_OnError						= new SOSMsgJOE("JOE_L_JCNodesForm_OnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JCNodesForm_OnError						= new SOSMsgJOE("JOE_Cbo_JCNodesForm_OnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNodesForm_Setback						= new SOSMsgJOE("JOE_M_JCNodesForm_Setback");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNodesForm_Suspend						= new SOSMsgJOE("JOE_M_JCNodesForm_Suspend");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_NewNode						= new SOSMsgJOE("JOE_B_JCNodesForm_NewNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_FullNode						= new SOSMsgJOE("JOE_B_JCNodesForm_FullNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_EndNode						= new SOSMsgJOE("JOE_B_JCNodesForm_EndNode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_FileSink						= new SOSMsgJOE("JOE_B_JCNodesForm_FileSink");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_RemoveFile						= new SOSMsgJOE("JOE_L_JCNodesForm_RemoveFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_RemoveFile						= new SOSMsgJOE("JOE_B_JCNodesForm_RemoveFile");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_MoveTo						= new SOSMsgJOE("JOE_L_JCNodesForm_MoveTo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_MoveTo						= new SOSMsgJOE("JOE_T_JCNodesForm_MoveTo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Insert						= new SOSMsgJOE("JOE_B_JCNodesForm_Insert");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JCNodesForm_Nodes						= new SOSMsgJOE("JOE_Tbl_JCNodesForm_Nodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_State						= new SOSMsgJOE("JOE_TCl_JCNodesForm_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_Node						= new SOSMsgJOE("JOE_TCl_JCNodesForm_Node");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_JobDir						= new SOSMsgJOE("JOE_TCl_JCNodesForm_JobDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_NextState						= new SOSMsgJOE("JOE_TCl_JCNodesForm_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_ErrorState						= new SOSMsgJOE("JOE_TCl_JCNodesForm_ErrorState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_OnError						= new SOSMsgJOE("JOE_TCl_JCNodesForm_OnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Up						= new SOSMsgJOE("JOE_B_JCNodesForm_Up");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Down						= new SOSMsgJOE("JOE_B_JCNodesForm_Down");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Reorder						= new SOSMsgJOE("JOE_B_JCNodesForm_Reorder");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Details						= new SOSMsgJOE("JOE_B_JCNodesForm_Details");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_AddMissingNodes						= new SOSMsgJOE("JOE_B_JCNodesForm_AddMissingNodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_Remove						= new SOSMsgJOE("JOE_B_JCNodesForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JCNodesForm_Remove						= new SOSMsgJOE("JOE_M_JCNodesForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JCNodesForm_FileOrderSources						= new SOSMsgJOE("JOE_G_JCNodesForm_FileOrderSources");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Directory						= new SOSMsgJOE("JOE_L_JCNodesForm_Directory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_Directory						= new SOSMsgJOE("JOE_T_JCNodesForm_Directory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_DelayAfterError						= new SOSMsgJOE("JOE_L_JCNodesForm_DelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_DelayAfterError						= new SOSMsgJOE("JOE_T_JCNodesForm_DelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_ApplyFileOrderSource						= new SOSMsgJOE("JOE_B_JCNodesForm_ApplyFileOrderSource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Regex						= new SOSMsgJOE("JOE_L_JCNodesForm_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_Regex						= new SOSMsgJOE("JOE_T_JCNodesForm_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Repeat						= new SOSMsgJOE("JOE_L_JCNodesForm_Repeat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_Repeat						= new SOSMsgJOE("JOE_T_JCNodesForm_Repeat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_Max						= new SOSMsgJOE("JOE_L_JCNodesForm_Max");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_Max						= new SOSMsgJOE("JOE_T_JCNodesForm_Max");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JCNodesForm_NextState						= new SOSMsgJOE("JOE_L_JCNodesForm_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JCNodesForm_NextState						= new SOSMsgJOE("JOE_T_JCNodesForm_NextState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_RemoveFileOrderSource						= new SOSMsgJOE("JOE_B_JCNodesForm_RemoveFileOrderSource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JCNodesForm_FileOrderSource						= new SOSMsgJOE("JOE_Tbl_JCNodesForm_FileOrderSource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_Directory						= new SOSMsgJOE("JOE_TCl_JCNodesForm_Directory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JCNodesForm_Regex						= new SOSMsgJOE("JOE_TCl_JCNodesForm_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JCNodesForm_NewFileOrderSource						= new SOSMsgJOE("JOE_B_JCNodesForm_NewFileOrderSource");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_NoRegex						= new SOSMsgJOE("JOE_M_NoRegex");

//	JobChainsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobChainsForm_JobChains						= new SOSMsgJOE("JOE_G_JobChainsForm_JobChains");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobChainsForm_JobChains						= new SOSMsgJOE("JOE_Tbl_JobChainsForm_JobChains");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobChainsForm_Name						= new SOSMsgJOE("JOE_TCl_JobChainsForm_Name");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobChainsForm_OrdersRecoverable						= new SOSMsgJOE("JOE_TCl_JobChainsForm_OrdersRecoverable");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobChainsForm_Visible						= new SOSMsgJOE("JOE_TCl_JobChainsForm_Visible");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainsForm_NewChain						= new SOSMsgJOE("JOE_B_JobChainsForm_NewChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainsForm_RemoveChain						= new SOSMsgJOE("JOE_B_JobChainsForm_RemoveChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChainsForm_RemoveChain						= new SOSMsgJOE("JOE_M_JobChainsForm_RemoveChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobChainsForm_Details						= new SOSMsgJOE("JOE_B_JobChainsForm_Details");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobChain						= new SOSMsgJOE("JOE_M_JobChain");
	
//	JobCommandExitCodesForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobCommand_Disabled						= new SOSMsgJOE("JOE_M_JobCommand_Disabled");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobCommands_Commands						= new SOSMsgJOE("JOE_G_JobCommands_Commands");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobCommands_Exitcode						= new SOSMsgJOE("JOE_Cbo_JobCommands_Exitcode");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommands_AddJob						= new SOSMsgJOE("JOE_B_JobCommands_AddJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommands_AddOrder						= new SOSMsgJOE("JOE_B_JobCommands_AddOrder");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommands_ExitCodes						= new SOSMsgJOE("JOE_L_JobCommands_ExitCodes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobCommands_Commands						= new SOSMsgJOE("JOE_Tbl_JobCommands_Commands");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobCommands_Command						= new SOSMsgJOE("JOE_TCl_JobCommands_Command");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobCommands_JobID						= new SOSMsgJOE("JOE_TCl_JobCommands_JobID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobCommands_JobChain						= new SOSMsgJOE("JOE_TCl_JobCommands_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobCommands_StartAt						= new SOSMsgJOE("JOE_TCl_JobCommands_StartAt");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommands_Remove						= new SOSMsgJOE("JOE_B_JobCommands_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobCommand_CommandsForJob						= new SOSMsgJOE("JOE_M_JobCommand_CommandsForJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobCommand_JobsAndOrders						= new SOSMsgJOE("JOE_G_JobCommand_JobsAndOrders");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_JobChain						= new SOSMsgJOE("JOE_L_JobCommand_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobCommand_JobChain						= new SOSMsgJOE("JOE_Cbo_JobCommand_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_Job						= new SOSMsgJOE("JOE_L_JobCommand_Job");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobCommand_Job						= new SOSMsgJOE("JOE_T_JobCommand_Job");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_StartAt						= new SOSMsgJOE("JOE_L_JobCommand_StartAt");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobCommand_StartAt						= new SOSMsgJOE("JOE_T_JobCommand_StartAt");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_Priority						= new SOSMsgJOE("JOE_L_JobCommand_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobCommand_Priority						= new SOSMsgJOE("JOE_T_JobCommand_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_Title						= new SOSMsgJOE("JOE_L_JobCommand_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobCommand_Title						= new SOSMsgJOE("JOE_T_JobCommand_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_OrderID						= new SOSMsgJOE("JOE_L_JobCommand_OrderID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_State						= new SOSMsgJOE("JOE_L_JobCommand_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobCommand_State						= new SOSMsgJOE("JOE_T_JobCommand_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_EndState						= new SOSMsgJOE("JOE_L_JobCommand_EndState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobCommand_EndState						= new SOSMsgJOE("JOE_Cbo_JobCommand_EndState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobCommand_Replace						= new SOSMsgJOE("JOE_L_JobCommand_Replace");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommand_Replace						= new SOSMsgJOE("JOE_B_JobCommand_Replace");
	
//	JobCommandsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobCommand_Commands						= new SOSMsgJOE("JOE_G_JobCommand_Commands");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommand_NewCommand						= new SOSMsgJOE("JOE_B_JobCommand_NewCommand");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobCommand_RemoveCommand						= new SOSMsgJOE("JOE_B_JobCommand_RemoveCommand");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobCommand_Table						= new SOSMsgJOE("JOE_Tbl_JobCommand_Table");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobCommand_Exitcode						= new SOSMsgJOE("JOE_TCl_JobCommand_Exitcode");
	

//	JobLockUseForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobLockUseForm_Use						= new SOSMsgJOE("JOE_G_JobLockUseForm_Use");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobLockUseForm_Lock						= new SOSMsgJOE("JOE_L_JobLockUseForm_Lock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobLockUseForm_LockUse						= new SOSMsgJOE("JOE_Cbo_JobLockUseForm_LockUse");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobLockUseForm_Exclusive						= new SOSMsgJOE("JOE_B_JobLockUseForm_Exclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobLockUseForm_ApplyLockUse						= new SOSMsgJOE("JOE_B_JobLockUseForm_ApplyLockUse");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobLockUseForm_Exclusive						= new SOSMsgJOE("JOE_L_JobLockUseForm_Exclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobLockUseForm_LockUseTable						= new SOSMsgJOE("JOE_Tbl_JobLockUseForm_LockUseTable");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobLockUseForm_Lock						= new SOSMsgJOE("JOE_TCl_JobLockUseForm_Lock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobLockUseForm_Exclusive						= new SOSMsgJOE("JOE_TCl_JobLockUseForm_Exclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobLockUseForm_NewLockUse						= new SOSMsgJOE("JOE_B_JobLockUseForm_NewLockUse");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobLockUseForm_RemoveLockUse						= new SOSMsgJOE("JOE_B_JobLockUseForm_RemoveLockUse");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobLockUseForm_Browse						= new SOSMsgJOE("JOE_B_JobLockUseForm_Browse");
	
//	JobMainOptionForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_JobMainOptionForm_MainOptions						= new SOSMsgJOE("JOE_M_JobMainOptionForm_MainOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_SchedulerID						= new SOSMsgJOE("JOE_L_JobMainOptionForm_SchedulerID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_SchedulerID						= new SOSMsgJOE("JOE_T_JobMainOptionForm_SchedulerID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_JavaOptions						= new SOSMsgJOE("JOE_L_JobMainOptionForm_JavaOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_JavaOptions						= new SOSMsgJOE("JOE_T_JobMainOptionForm_JavaOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_IgnoreSignals						= new SOSMsgJOE("JOE_L_JobMainOptionForm_IgnoreSignals");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_IgnoreSignals						= new SOSMsgJOE("JOE_T_JobMainOptionForm_IgnoreSignals");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobMainOptionForm_Add						= new SOSMsgJOE("JOE_B_JobMainOptionForm_Add");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobMainOptionForm_Signals						= new SOSMsgJOE("JOE_Cbo_JobMainOptionForm_Signals");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_Priority						= new SOSMsgJOE("JOE_L_JobMainOptionForm_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobMainOptionForm_Priority						= new SOSMsgJOE("JOE_Cbo_JobMainOptionForm_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_Visible						= new SOSMsgJOE("JOE_L_JobMainOptionForm_Visible");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_JobMainOptionForm_Visible						= new SOSMsgJOE("JOE_Cbo_JobMainOptionForm_Visible");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_MinTasks						= new SOSMsgJOE("JOE_L_JobMainOptionForm_MinTasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_MinTasks						= new SOSMsgJOE("JOE_T_JobMainOptionForm_MinTasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_Tasks						= new SOSMsgJOE("JOE_L_JobMainOptionForm_Tasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_Tasks						= new SOSMsgJOE("JOE_T_JobMainOptionForm_Tasks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_Timeout						= new SOSMsgJOE("JOE_L_JobMainOptionForm_Timeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_Timeout						= new SOSMsgJOE("JOE_T_JobMainOptionForm_Timeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_IdleTimeout						= new SOSMsgJOE("JOE_L_JobMainOptionForm_IdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_IdleTimeout						= new SOSMsgJOE("JOE_T_JobMainOptionForm_IdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_IdleTimeoutFormat						= new SOSMsgJOE("JOE_L_JobMainOptionForm_IdleTimeoutFormat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_WarnIfLonger						= new SOSMsgJOE("JOE_L_JobMainOptionForm_WarnIfLonger");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_WarnIfLonger						= new SOSMsgJOE("JOE_T_JobMainOptionForm_WarnIfLonger");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_WarnIfLongerFormat						= new SOSMsgJOE("JOE_L_JobMainOptionForm_WarnIfLongerFormat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_WarnIfShorter						= new SOSMsgJOE("JOE_L_JobMainOptionForm_WarnIfShorter");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobMainOptionForm_WarnIfShorter						= new SOSMsgJOE("JOE_T_JobMainOptionForm_WarnIfShorter");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_WarnIfShorterFormat						= new SOSMsgJOE("JOE_L_JobMainOptionForm_WarnIfShorterFormat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobMainOptionForm_ForceIdleTimeout						= new SOSMsgJOE("JOE_L_JobMainOptionForm_ForceIdleTimeout");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobMainOptionForm_ForceIdleTimeout						= new SOSMsgJOE("JOE_B_JobMainOptionForm_ForceIdleTimeout");
	
//	JobOptionsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobOptionsForm_RunOptions						= new SOSMsgJOE("JOE_G_JobOptionsForm_RunOptions");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobOptionsForm_StartWhenDirectoryChanged						= new SOSMsgJOE("JOE_G_JobOptionsForm_StartWhenDirectoryChanged");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_WatchDirectory						= new SOSMsgJOE("JOE_L_JobOptionsForm_WatchDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_WatchDirectory						= new SOSMsgJOE("JOE_T_JobOptionsForm_WatchDirectory");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_FileRegex						= new SOSMsgJOE("JOE_L_JobOptionsForm_FileRegex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_FileRegex						= new SOSMsgJOE("JOE_T_JobOptionsForm_FileRegex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_ApplyDir						= new SOSMsgJOE("JOE_B_JobOptionsForm_ApplyDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_NewDir						= new SOSMsgJOE("JOE_B_JobOptionsForm_NewDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_RemoveDir						= new SOSMsgJOE("JOE_B_JobOptionsForm_RemoveDir");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobOptionsForm_DelayAfterError						= new SOSMsgJOE("JOE_G_JobOptionsForm_DelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_ErrorCount						= new SOSMsgJOE("JOE_L_JobOptionsForm_ErrorCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_ErrorCount						= new SOSMsgJOE("JOE_T_JobOptionsForm_ErrorCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_Stop						= new SOSMsgJOE("JOE_B_JobOptionsForm_Stop");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_Delay						= new SOSMsgJOE("JOE_B_JobOptionsForm_Delay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_ErrorHours						= new SOSMsgJOE("JOE_T_JobOptionsForm_ErrorHours");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_ErrorMinutes						= new SOSMsgJOE("JOE_T_JobOptionsForm_ErrorMinutes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_ErrorSeconds						= new SOSMsgJOE("JOE_T_JobOptionsForm_ErrorSeconds");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_DelayFormat						= new SOSMsgJOE("JOE_L_JobOptionsForm_DelayFormat");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_ApplyDelay						= new SOSMsgJOE("JOE_B_JobOptionsForm_ApplyDelay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_NewDelayAfterError						= new SOSMsgJOE("JOE_B_JobOptionsForm_NewDelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_RemoveDelay						= new SOSMsgJOE("JOE_B_JobOptionsForm_RemoveDelay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobOptionsForm_DelayOrderAfterSetBack						= new SOSMsgJOE("JOE_G_JobOptionsForm_DelayOrderAfterSetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_SetBackCount						= new SOSMsgJOE("JOE_L_JobOptionsForm_SetBackCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_SetBackCount						= new SOSMsgJOE("JOE_T_JobOptionsForm_SetBackCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_IsMax						= new SOSMsgJOE("JOE_B_JobOptionsForm_IsMax");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_JobOptionsForm_Delay						= new SOSMsgJOE("JOE_L_JobOptionsForm_Delay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_SetBackHours						= new SOSMsgJOE("JOE_T_JobOptionsForm_SetBackHours");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_SetBackMinutes						= new SOSMsgJOE("JOE_T_JobOptionsForm_SetBackMinutes");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_JobOptionsForm_SetBackSeconds						= new SOSMsgJOE("JOE_T_JobOptionsForm_SetBackSeconds");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_ApplySetBack						= new SOSMsgJOE("JOE_B_JobOptionsForm_ApplySetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_NewSetBack						= new SOSMsgJOE("JOE_B_JobOptionsForm_NewSetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobOptionsForm_RemoveSetback						= new SOSMsgJOE("JOE_B_JobOptionsForm_RemoveSetback");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobOptionsForm_ErrorDelay						= new SOSMsgJOE("JOE_Tbl_JobOptionsForm_ErrorDelay");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_ErrorCount						= new SOSMsgJOE("JOE_TCl_JobOptionsForm_ErrorCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_Delayhhmmss						= new SOSMsgJOE("JOE_TCl_JobOptionsForm_Delayhhmmss");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobOptionsForm_SetBack						= new SOSMsgJOE("JOE_Tbl_JobOptionsForm_SetBack");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_SetBackCount						= new SOSMsgJOE("JOE_TCl_JobOptionsForm_SetBackCount");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_IsMax						= new SOSMsgJOE("JOE_TCl_JobOptionsForm_IsMax");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobOptionsForm_Dirs						= new SOSMsgJOE("JOE_Tbl_JobOptionsForm_Dirs");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_Dir						= new SOSMsgJOE("JOE_Tbl_JobOptionsForm_Dirs");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobOptionsForm_Regex						= new SOSMsgJOE("JOE_TCl_JobOptionsForm_Regex");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_ZeroNotAllowed						= new SOSMsgJOE("JOE_M_ZeroNotAllowed");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0032						= new SOSMsgJOE("JOE_M_0032");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0033						= new SOSMsgJOE("JOE_M_0033");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0034						= new SOSMsgJOE("JOE_M_0034");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0035						= new SOSMsgJOE("JOE_M_0035");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_Stop						= new SOSMsgJOE("JOE_M_Stop");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0036						= new SOSMsgJOE("JOE_M_0036");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0037						= new SOSMsgJOE("JOE_M_0037");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0038						= new SOSMsgJOE("JOE_M_0038");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0039						= new SOSMsgJOE("JOE_M_0039");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_Yes						= new SOSMsgJOE("JOE_M_Yes");
	
//	JobsForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_JobsForm_Jobs						= new SOSMsgJOE("JOE_G_JobsForm_Jobs");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobsForm_NewStandaloneJob						= new SOSMsgJOE("JOE_B_JobsForm_NewStandaloneJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobsForm_NewOrderJob						= new SOSMsgJOE("JOE_B_JobsForm_NewOrderJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobsForm_JobWizard						= new SOSMsgJOE("JOE_B_JobsForm_JobWizard");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_0040						= new SOSMsgJOE("JOE_M_0040");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_JobsForm_RemoveJob						= new SOSMsgJOE("JOE_B_JobsForm_RemoveJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_RemoveJob						= new SOSMsgJOE("JOE_M_RemoveJob");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_JobsForm_Table						= new SOSMsgJOE("JOE_Tbl_JobsForm_Table");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_Disabled						= new SOSMsgJOE("JOE_TCl_JobsForm_Disabled");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_Name						= new SOSMsgJOE("JOE_TCl_JobsForm_Name");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_Title						= new SOSMsgJOE("JOE_TCl_JobsForm_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_SchedulerID						= new SOSMsgJOE("JOE_TCl_JobsForm_SchedulerID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_ProcessClass						= new SOSMsgJOE("JOE_TCl_JobsForm_ProcessClass");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_JobsForm_Order						= new SOSMsgJOE("JOE_TCl_JobsForm_Order");

//	LocksForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_LocksForm_Locks						= new SOSMsgJOE("JOE_G_LocksForm_Locks");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_LocksForm_Lock						= new SOSMsgJOE("JOE_L_LocksForm_Lock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_LocksForm_Lock						= new SOSMsgJOE("JOE_T_LocksForm_Lock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_LocksForm_Apply						= new SOSMsgJOE("JOE_B_LocksForm_Apply");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_LocksForm_MaxNonExclusive						= new SOSMsgJOE("JOE_L_LocksForm_MaxNonExclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_LocksForm_UnlimitedNonExclusive						= new SOSMsgJOE("JOE_B_LocksForm_UnlimitedNonExclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Sp_LocksForm_MaxNonExclusive						= new SOSMsgJOE("JOE_Sp_LocksForm_MaxNonExclusive");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_LocksForm_NewLock						= new SOSMsgJOE("JOE_B_LocksForm_NewLock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_LocksForm_RemoveLock						= new SOSMsgJOE("JOE_B_LocksForm_RemoveLock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_LocksForm_Table						= new SOSMsgJOE("JOE_Tbl_LocksForm_Table");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_LocksForm_Lock						= new SOSMsgJOE("JOE_TCl_LocksForm_Lock");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_LocksForm_MaxNonExclusive						= new SOSMsgJOE("JOE_TCl_LocksForm_MaxNonExclusive");
	
//	MailForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_MailForm_Mail						= new SOSMsgJOE("JOE_G_MailForm_Mail");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailOnError						= new SOSMsgJOE("JOE_L_MailForm_MailOnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_MailOnError						= new SOSMsgJOE("JOE_Cbo_MailForm_MailOnError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailOnWarning						= new SOSMsgJOE("JOE_L_MailForm_MailOnWarning");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_MailOnWarning						= new SOSMsgJOE("JOE_Cbo_MailForm_MailOnWarning");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailOnSuccess						= new SOSMsgJOE("JOE_L_MailForm_MailOnSuccess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_MailOnSuccess						= new SOSMsgJOE("JOE_Cbo_MailForm_MailOnSuccess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailOnProcess						= new SOSMsgJOE("JOE_L_MailForm_MailOnProcess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_MailOnProcess						= new SOSMsgJOE("JOE_Cbo_MailForm_MailOnProcess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailOnDelayAfterError						= new SOSMsgJOE("JOE_L_MailForm_MailOnDelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_MailOnDelayAfterError						= new SOSMsgJOE("JOE_Cbo_MailForm_MailOnDelayAfterError");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailTo						= new SOSMsgJOE("JOE_L_MailForm_MailTo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_MailForm_MailTo						= new SOSMsgJOE("JOE_T_MailForm_MailTo");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailCC						= new SOSMsgJOE("JOE_L_MailForm_MailCC");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_MailForm_MailCC						= new SOSMsgJOE("JOE_T_MailForm_MailCC");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_MailBCC						= new SOSMsgJOE("JOE_L_MailForm_MailBCC");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_MailForm_MailBCC						= new SOSMsgJOE("JOE_T_MailForm_MailBCC");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_LogLevel						= new SOSMsgJOE("JOE_L_MailForm_LogLevel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_LogLevel						= new SOSMsgJOE("JOE_Cbo_MailForm_LogLevel");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_History						= new SOSMsgJOE("JOE_L_MailForm_History");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_History						= new SOSMsgJOE("JOE_Cbo_MailForm_History");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_HistoryOnProcess						= new SOSMsgJOE("JOE_L_MailForm_HistoryOnProcess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_HistoryOnProcess						= new SOSMsgJOE("JOE_Cbo_MailForm_HistoryOnProcess");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_MailForm_HistoryWithLog						= new SOSMsgJOE("JOE_L_MailForm_HistoryWithLog");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_MailForm_HistoryWithLog						= new SOSMsgJOE("JOE_Cbo_MailForm_HistoryWithLog");

//	OrderForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_OrderForm_Order						= new SOSMsgJOE("JOE_G_OrderForm_Order");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_OrderID						= new SOSMsgJOE("JOE_L_OrderForm_OrderID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_OrderForm_OrderID						= new SOSMsgJOE("JOE_T_OrderForm_OrderID");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_JobChain						= new SOSMsgJOE("JOE_L_OrderForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_OrderForm_JobChain						= new SOSMsgJOE("JOE_Cbo_OrderForm_JobChain");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_Title						= new SOSMsgJOE("JOE_L_OrderForm_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_OrderForm_Title						= new SOSMsgJOE("JOE_T_OrderForm_Title");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_Priority						= new SOSMsgJOE("JOE_L_OrderForm_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_OrderForm_Priority						= new SOSMsgJOE("JOE_T_OrderForm_Priority");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_State						= new SOSMsgJOE("JOE_L_OrderForm_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_T_OrderForm_State						= new SOSMsgJOE("JOE_T_OrderForm_State");
	@I18NMsg
	public static final SOSMsgJOE	JOE_L_OrderForm_EndState						= new SOSMsgJOE("JOE_L_OrderForm_EndState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_OrderForm_EndState						= new SOSMsgJOE("JOE_Cbo_OrderForm_EndState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Cbo_OrderForm_State2						= new SOSMsgJOE("JOE_Cbo_OrderForm_State2");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_OrderForm_Remove						= new SOSMsgJOE("JOE_B_OrderForm_Remove");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_OrderForm_RemoveState						= new SOSMsgJOE("JOE_M_OrderForm_RemoveState");
	@I18NMsg
	public static final SOSMsgJOE	JOE_M_OrderForm_RemoveFailed						= new SOSMsgJOE("JOE_M_OrderForm_RemoveFailed");


	
	

//	OrdersForm
	@I18NMsg
	public static final SOSMsgJOE	JOE_G_OrdersForm_Orders						= new SOSMsgJOE("JOE_G_OrdersForm_Orders");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_OrdersForm_NewOrder						= new SOSMsgJOE("JOE_B_OrdersForm_NewOrder");
	@I18NMsg
	public static final SOSMsgJOE	JOE_B_OrdersForm_RemoveOrder						= new SOSMsgJOE("JOE_B_OrdersForm_RemoveOrder");
	@I18NMsg
	public static final SOSMsgJOE	JOE_Tbl_OrdersForm_Table						= new SOSMsgJOE("JOE_Tbl_OrdersForm_Table");
	@I18NMsg
	public static final SOSMsgJOE	JOE_TCl_OrdersForm_OrderNameID						= new SOSMsgJOE("JOE_TCl_OrdersForm_OrderNameID");



	// SchedulerListener
	@I18NMsg
	public static final SOSMsgJOE	JOE_Msg_0001										= new SOSMsgJOE("JOE_Msg_0001");

	// @I18NMsg
	// public static final SOSMsgJOE JOE_Comment = new SOSMsgJOE("Comment"); // "Comment";

	// public SOSJOEMessageCodes() {
	// // TODO Auto-generated constructor stub
	// }

	public SOSJOEMessageCodes(Group parent, int style) {
		super(parent, style);
	}

	public SOSJOEMessageCodes(Composite parent, int style) {
		super(parent, style);
	}
}
