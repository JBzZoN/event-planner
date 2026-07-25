import { PackageItem } from "./package-item";

export interface PackageGroup {
  groupId: (number|null);
  groupName: string;
  packageGroupItem: PackageItem[];
}
