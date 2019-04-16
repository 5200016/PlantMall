import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysOrder } from 'app/shared/model/sys-order.model';
import { SysOrderService } from './sys-order.service';
import { SysOrderComponent } from './sys-order.component';
import { SysOrderDetailComponent } from './sys-order-detail.component';
import { SysOrderUpdateComponent } from './sys-order-update.component';
import { SysOrderDeletePopupComponent } from './sys-order-delete-dialog.component';
import { ISysOrder } from 'app/shared/model/sys-order.model';

@Injectable({ providedIn: 'root' })
export class SysOrderResolve implements Resolve<ISysOrder> {
    constructor(private service: SysOrderService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysOrder> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysOrder>) => response.ok),
                map((sysOrder: HttpResponse<SysOrder>) => sysOrder.body)
            );
        }
        return of(new SysOrder());
    }
}

export const sysOrderRoute: Routes = [
    {
        path: 'sys-order',
        component: SysOrderComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order/:id/view',
        component: SysOrderDetailComponent,
        resolve: {
            sysOrder: SysOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order/new',
        component: SysOrderUpdateComponent,
        resolve: {
            sysOrder: SysOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-order/:id/edit',
        component: SysOrderUpdateComponent,
        resolve: {
            sysOrder: SysOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysOrderPopupRoute: Routes = [
    {
        path: 'sys-order/:id/delete',
        component: SysOrderDeletePopupComponent,
        resolve: {
            sysOrder: SysOrderResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
