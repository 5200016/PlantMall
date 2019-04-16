import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysProduct } from 'app/shared/model/sys-product.model';

type EntityResponseType = HttpResponse<ISysProduct>;
type EntityArrayResponseType = HttpResponse<ISysProduct[]>;

@Injectable({ providedIn: 'root' })
export class SysProductService {
    public resourceUrl = SERVER_API_URL + 'api/sys-products';

    constructor(protected http: HttpClient) {}

    create(sysProduct: ISysProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysProduct);
        return this.http
            .post<ISysProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysProduct: ISysProduct): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysProduct);
        return this.http
            .put<ISysProduct>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysProduct: ISysProduct): ISysProduct {
        const copy: ISysProduct = Object.assign({}, sysProduct, {
            createTime: sysProduct.createTime != null && sysProduct.createTime.isValid() ? sysProduct.createTime.toJSON() : null,
            updateTime: sysProduct.updateTime != null && sysProduct.updateTime.isValid() ? sysProduct.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysProduct: ISysProduct) => {
                sysProduct.createTime = sysProduct.createTime != null ? moment(sysProduct.createTime) : null;
                sysProduct.updateTime = sysProduct.updateTime != null ? moment(sysProduct.updateTime) : null;
            });
        }
        return res;
    }
}
