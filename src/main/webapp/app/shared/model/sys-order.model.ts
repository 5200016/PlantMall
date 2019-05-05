import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysReceiverAddress } from 'app/shared/model//sys-receiver-address.model';
import { ISysMaintenancePersonnel } from 'app/shared/model//sys-maintenance-personnel.model';
import { ISysOrderProduct } from 'app/shared/model//sys-order-product.model';

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
    maintenanceTime?: any;
    maintenanceDescription?: any;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    receiverAddress?: ISysReceiverAddress;
    maintenancePersonnel?: ISysMaintenancePersonnel;
    orderProducts?: ISysOrderProduct[];
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
        public maintenanceTime?: any,
        public maintenanceDescription?: any,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public receiverAddress?: ISysReceiverAddress,
        public maintenancePersonnel?: ISysMaintenancePersonnel,
        public orderProducts?: ISysOrderProduct[]
    ) {}
}
