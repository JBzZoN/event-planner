import { ItemDto } from "./item-dto";

export interface GroupDto {
    groupId: number;
    groupName: string;
    items : Array<ItemDto>;
}
