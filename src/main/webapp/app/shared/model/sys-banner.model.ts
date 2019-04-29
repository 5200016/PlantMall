import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';
import { ISysClassify } from 'app/shared/model//sys-classify.model';

export interface ISysBanner {
    id?: number;
    image?: string;
    path?: string;
    type?: number;
    sort?: number;
    createTime?: Moment;
    updateTime?: Moment;
    product?: ISysProduct;
    classify?: ISysClassify;
}

export class SysBanner implements ISysBanner {
    constructor(
        public id?: number,
        public image?: string,
        public path?: string,
        public type?: number,
        public sort?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public product?: ISysProduct,
        public classify?: ISysClassify
    ) {}
}
