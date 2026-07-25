import { GroupDto } from "./group-dto";

export interface PackageDto {
    packageId : number;
    packageName : string;
    packagePrice : number;
    groups : Array<GroupDto>;
}
