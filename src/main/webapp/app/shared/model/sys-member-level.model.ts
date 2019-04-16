import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';

export interface ISysMemberLevel {
    id?: number;
    name?: string;
    level?: number;
    leftValue?: number;
    rightValue?: number;
    discount?: number;
    createTime?: Moment;
    updateTime?: Moment;
    users?: ISysUser[];
}

export class SysMemberLevel implements ISysMemberLevel {
    constructor(
        public id?: number,
        public name?: string,
        public level?: number,
        public leftValue?: number,
        public rightValue?: number,
        public discount?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public users?: ISysUser[]
    ) {}
}
