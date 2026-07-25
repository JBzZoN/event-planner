import { PackageGroup } from "./package-group";
import { PlannerDetail } from "./planner-detail";

export interface VendorPackage {
  packageId: (number|null);
  packageName: string;
  packagePrice: number;
  packageGroup: PackageGroup[];
}