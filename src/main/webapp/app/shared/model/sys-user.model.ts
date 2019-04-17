import { Moment } from 'moment';
import { ISysMemberLevel } from 'app/shared/model//sys-member-level.model';
import { ISysRole } from 'app/shared/model//sys-role.model';
import { ISysReceiverAddress } from 'app/shared/model//sys-receiver-address.model';
import { ISysOrder } from 'app/shared/model//sys-order.model';
import { ISysCollection } from 'app/shared/model//sys-collection.model';

export interface ISysUser {
    id?: number;
    openid?: string;
    phone?: string;
    sessionKey?: string;
    username?: string;
    avatar?: string;
    nickname?: string;
    sex?: string;
    integral?: number;
    growthValue?: number;
    createTime?: Moment;
    updateTime?: Moment;
    memberLevel?: ISysMemberLevel;
    roles?: ISysRole[];
    receiveAddresses?: ISysReceiverAddress[];
    orders?: ISysOrder[];
    collections?: ISysCollection[];
}

export class SysUser implements ISysUser {
    constructor(
        public id?: number,
        public openid?: string,
        public phone?: string,
        public sessionKey?: string,
        public username?: string,
        public avatar?: string,
        public nickname?: string,
        public sex?: string,
        public integral?: number,
        public growthValue?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public memberLevel?: ISysMemberLevel,
        public roles?: ISysRole[],
        public receiveAddresses?: ISysReceiverAddress[],
        public orders?: ISysOrder[],
        public collections?: ISysCollection[]
    ) {}
}