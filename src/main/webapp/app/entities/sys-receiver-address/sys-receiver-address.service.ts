import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

type EntityResponseType = HttpResponse<ISysReceiverAddress>;
type EntityArrayResponseType = HttpResponse<ISysReceiverAddress[]>;

@Injectable({ providedIn: 'root' })
export class SysReceiverAddressService {
    public resourceUrl = SERVER_API_URL + 'api/sys-receiver-addresses';

    constructor(protected http: HttpClient) {}

    create(sysReceiverAddress: ISysReceiverAddress): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysReceiverAddress);
        return this.http
            .post<ISysReceiverAddress>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysReceiverAddress: ISysReceiverAddress): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysReceiverAddress);
        return this.http
            .put<ISysReceiverAddress>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysReceiverAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysReceiverAddress[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysReceiverAddress: ISysReceiverAddress): ISysReceiverAddress {
        const copy: ISysReceiverAddress = Object.assign({}, sysReceiverAddress, {
            createTime:
                sysReceiverAddress.createTime != null && sysReceiverAddress.createTime.isValid()
                    ? sysReceiverAddress.createTime.toJSON()
                    : null,
            updateTime:
                sysReceiverAddress.updateTime != null && sysReceiverAddress.updateTime.isValid()
                    ? sysReceiverAddress.updateTime.toJSON()
                    : null
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
            res.body.forEach((sysReceiverAddress: ISysReceiverAddress) => {
                sysReceiverAddress.createTime = sysReceiverAddress.createTime != null ? moment(sysReceiverAddress.createTime) : null;
                sysReceiverAddress.updateTime = sysReceiverAddress.updateTime != null ? moment(sysReceiverAddress.updateTime) : null;
            });
        }
        return res;
    }
}
