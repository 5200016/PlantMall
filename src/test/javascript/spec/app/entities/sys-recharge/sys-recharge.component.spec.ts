/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysRechargeComponent } from 'app/entities/sys-recharge/sys-recharge.component';
import { SysRechargeService } from 'app/entities/sys-recharge/sys-recharge.service';
import { SysRecharge } from 'app/shared/model/sys-recharge.model';

describe('Component Tests', () => {
    describe('SysRecharge Management Component', () => {
        let comp: SysRechargeComponent;
        let fixture: ComponentFixture<SysRechargeComponent>;
        let service: SysRechargeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRechargeComponent],
                providers: []
            })
                .overrideTemplate(SysRechargeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysRechargeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysRechargeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysRecharge(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysRecharges[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
