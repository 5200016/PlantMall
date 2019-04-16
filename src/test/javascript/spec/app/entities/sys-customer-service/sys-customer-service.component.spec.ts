/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCustomerServiceComponent } from 'app/entities/sys-customer-service/sys-customer-service.component';
import { SysCustomerServiceService } from 'app/entities/sys-customer-service/sys-customer-service.service';
import { SysCustomerService } from 'app/shared/model/sys-customer-service.model';

describe('Component Tests', () => {
    describe('SysCustomerService Management Component', () => {
        let comp: SysCustomerServiceComponent;
        let fixture: ComponentFixture<SysCustomerServiceComponent>;
        let service: SysCustomerServiceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCustomerServiceComponent],
                providers: []
            })
                .overrideTemplate(SysCustomerServiceComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCustomerServiceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCustomerServiceService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCustomerService(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCustomerServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
