import { PlannerStatus } from "./planner-status";

export interface VendorSummary {
    orgId: number;
	orgName: string;
	officeAddress: string;
	status: PlannerStatus;
	suspendUntil : Date
}
