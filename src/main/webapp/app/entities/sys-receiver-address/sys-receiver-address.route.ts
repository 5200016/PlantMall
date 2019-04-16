import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';
import { SysReceiverAddressService } from './sys-receiver-address.service';
import { SysReceiverAddressComponent } from './sys-receiver-address.component';
import { SysReceiverAddressDetailComponent } from './sys-receiver-address-detail.component';
import { SysReceiverAddressUpdateComponent } from './sys-receiver-address-update.component';
import { SysReceiverAddressDeletePopupComponent } from './sys-receiver-address-delete-dialog.component';
import { ISysReceiverAddress } from 'app/shared/model/sys-receiver-address.model';

@Injectable({ providedIn: 'root' })
export class SysReceiverAddressResolve implements Resolve<ISysReceiverAddress> {
    constructor(private service: SysReceiverAddressService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysReceiverAddress> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysReceiverAddress>) => response.ok),
                map((sysReceiverAddress: HttpResponse<SysReceiverAddress>) => sysReceiverAddress.body)
            );
        }
        return of(new SysReceiverAddress());
    }
}

export const sysReceiverAddressRoute: Routes = [
    {
        path: 'sys-receiver-address',
        component: SysReceiverAddressComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReceiverAddress.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-receiver-address/:id/view',
        component: SysReceiverAddressDetailComponent,
        resolve: {
            sysReceiverAddress: SysReceiverAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReceiverAddress.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-receiver-address/new',
        component: SysReceiverAddressUpdateComponent,
        resolve: {
            sysReceiverAddress: SysReceiverAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReceiverAddress.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-receiver-address/:id/edit',
        component: SysReceiverAddressUpdateComponent,
        resolve: {
            sysReceiverAddress: SysReceiverAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReceiverAddress.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysReceiverAddressPopupRoute: Routes = [
    {
        path: 'sys-receiver-address/:id/delete',
        component: SysReceiverAddressDeletePopupComponent,
        resolve: {
            sysReceiverAddress: SysReceiverAddressResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReceiverAddress.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
