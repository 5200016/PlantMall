/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysOrderComponent } from 'app/entities/sys-order/sys-order.component';
import { SysOrderService } from 'app/entities/sys-order/sys-order.service';
import { SysOrder } from 'app/shared/model/sys-order.model';

describe('Component Tests', () => {
    describe('SysOrder Management Component', () => {
        let comp: SysOrderComponent;
        let fixture: ComponentFixture<SysOrderComponent>;
        let service: SysOrderService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysOrderComponent],
                providers: []
            })
                .overrideTemplate(SysOrderComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysOrderComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysOrderService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysOrder(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
