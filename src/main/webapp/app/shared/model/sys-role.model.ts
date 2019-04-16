import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';

export interface ISysRole {
    id?: number;
    code?: string;
    name?: string;
    remark?: string;
    createTime?: Moment;
    updateTime?: Moment;
    users?: ISysUser[];
}

export class SysRole implements ISysRole {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public remark?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public users?: ISysUser[]
    ) {}
}
