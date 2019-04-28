import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';
import { ISysReceiverAddress } from 'app/shared/model//sys-receiver-address.model';

export interface ISysOrder {
    id?: number;
    tradeNo?: string;
    payNo?: string;
    price?: number;
    type?: number;
    payType?: number;
    status?: number;
    number?: number;
    description?: any;
    maintenancePlanStatus?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    product?: ISysProduct;
    receiverAddress?: ISysReceiverAddress;
}

export class SysOrder implements ISysOrder {
    constructor(
        public id?: number,
        public tradeNo?: string,
        public payNo?: string,
        public price?: number,
        public type?: number,
        public payType?: number,
        public status?: number,
        public number?: number,
        public description?: any,
        public maintenancePlanStatus?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public product?: ISysProduct,
        public receiverAddress?: ISysReceiverAddress
    ) {}
}
