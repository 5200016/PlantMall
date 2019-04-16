import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysOrder } from 'app/shared/model//sys-order.model';

export interface ISysReceiverAddress {
    id?: number;
    openid?: string;
    status?: number;
    address?: any;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    products?: ISysOrder[];
}

export class SysReceiverAddress implements ISysReceiverAddress {
    constructor(
        public id?: number,
        public openid?: string,
        public status?: number,
        public address?: any,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public products?: ISysOrder[]
    ) {}
}
