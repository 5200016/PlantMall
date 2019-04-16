import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';
import { ISysReceiverAddress } from 'app/shared/model//sys-receiver-address.model';

export interface ISysOrder {
    id?: number;
    tradeNo?: string;
    price?: number;
    type?: number;
    number?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    product?: ISysProduct;
    receiveAddresses?: ISysReceiverAddress[];
}

export class SysOrder implements ISysOrder {
    constructor(
        public id?: number,
        public tradeNo?: string,
        public price?: number,
        public type?: number,
        public number?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public product?: ISysProduct,
        public receiveAddresses?: ISysReceiverAddress[]
    ) {}
}
