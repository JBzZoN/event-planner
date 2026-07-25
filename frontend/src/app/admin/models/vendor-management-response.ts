import { VendorStats } from "./vendor-stats";
import { VendorSummary } from "./vendor-summary";

export interface VendorManagementResponse {
    stats : VendorStats;
    vendorList : Array<VendorSummary>;
}
